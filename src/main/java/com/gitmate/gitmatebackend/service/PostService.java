package com.gitmate.gitmatebackend.service;

import com.gitmate.gitmatebackend.Repositories.PostRepo;
import com.gitmate.gitmatebackend.model.Post;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepo postRepo;
    @Autowired
    UserService userService;

    public List<Post> getPosts() {
        return postRepo.findAll();
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