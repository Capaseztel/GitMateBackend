package com.gitmate.gitmatebackend.Service;

import com.gitmate.gitmatebackend.Repositories.PostRepo;
import com.gitmate.gitmatebackend.Domain.Post;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class PostService {
    @Autowired
    PostRepo postRepo;
    @Autowired
    UserService userService;

    public Page<Post> getPosts(Pageable pageable) {
        return postRepo.findAllPageable(pageable);
    }

    public Post getPostByID(Long id) {
        return postRepo.findById(id).orElse(null);
    }

    @Transactional
    public Post addPost(Post post, Long idUser) {
        post.setAuthor(userService.getUserById(idUser));
        post = postRepo.save(post);
        userService.addPost(post);
        return post;
    }


    @Transactional
    public Post addComment(Long idParent, Post comment, Long idUser) {
        comment.setAuthor(userService.getUserById(idUser));
        Post parent = postRepo.findById(idParent).orElse(null);
        if (parent == null) {
            return null;
        }
        comment.setParent(parent);
        comment = this.addPost(comment, comment.getAuthor().getId());
        parent.getComments().add(comment);
        postRepo.save(parent);
        return comment;
    }
}