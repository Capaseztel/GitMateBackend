package com.gitmate.gitmatebackend.service;

import com.gitmate.gitmatebackend.Repositories.PostRepo;
import com.gitmate.gitmatebackend.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    public List<Post> getPosts() {
        return postRepo.findAll();
    }
}
