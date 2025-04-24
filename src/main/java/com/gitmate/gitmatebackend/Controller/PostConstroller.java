package com.gitmate.gitmatebackend.Controller;

import com.gitmate.gitmatebackend.Domain.Post;
import com.gitmate.gitmatebackend.Service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/v1/api/posts")
public class PostConstroller {
    @Autowired
    PostService postService;

    @GetMapping({"/", ""})
    public Page<Post> getPosts(Pageable pageable) {
        log.info("Getting all posts");
        return postService.getPosts(pageable);
    }

    @GetMapping({"/{id}"})
    public Post getPosts(@PathVariable("id") Long id) {
        log.info("Getting post by id = " + id.toString());
        return postService.getPostByID(id);
    }

    @PostMapping({"/", ""})
    public Post addPost(@RequestBody Post post, @RequestParam("id") Long id) {
        log.info("Adding post");
        return postService.addPost(post, id);
    }

    @PostMapping({"/{id}"})
    public Post addComment(@PathVariable("id") Long id, @RequestBody Post comment, @RequestParam("userId") Long idUser) {
        log.info("Adding comment to post by id = {}", id.toString());
        return postService.addComment(id, comment, idUser);
    }
}
