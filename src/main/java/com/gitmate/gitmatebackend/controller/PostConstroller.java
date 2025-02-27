package com.gitmate.gitmatebackend.controller;

import com.gitmate.gitmatebackend.model.Post;
import com.gitmate.gitmatebackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/posts")
public class PostConstroller {
    @Autowired
    PostService postService;

    @GetMapping({"/", ""})
    public List<Post> getPosts() {
        return postService.getPosts();
    }
}
