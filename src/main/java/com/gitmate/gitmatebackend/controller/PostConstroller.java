package com.gitmate.gitmatebackend.controller;

import com.gitmate.gitmatebackend.model.Post;
import com.gitmate.gitmatebackend.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/posts")
public class PostConstroller {
    @Autowired
    PostService postService;

    @GetMapping({"/", ""})
    public List<Post> getPosts() {
        log.info("Getting all posts");
        return postService.getPosts();
    }

    @GetMapping({"/{id}"})
    public Post getPosts(@PathVariable("id") Long id) {
        log.info("Getting post by id = " + id.toString());
        return postService.getPostByID(id);
    }

    @PostMapping({"/", ""})
    public Post addPost(@RequestBody Post post) {
        log.info("Adding post");
        return postService.addPost(post);
    }

    @PostMapping({"/{id}"})
    public Post addComment(@PathVariable("id") Long id, @RequestBody Post comment) {
        log.info("Adding comment to post by id = " + id.toString());
        return postService.addComment(id, comment);
    }
}
