package com.gitmate.gitmatebackend.service;

import com.gitmate.gitmatebackend.Repositories.UserRepo;
import com.gitmate.gitmatebackend.model.Post;
import com.gitmate.gitmatebackend.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public User loginUser(String UN, String PW) {
        return userRepo.getUserByUniqueNameAndPassword(UN, PW);
    }

    public User updateUser(Long id, User user) {
        user.setId(id);
        return userRepo.save(user);
    }

    @Transactional
    public User addPost(Post post) {
        User user = userRepo.findById(post.getAuthor().getId()).orElse(null);
        if (user != null) {
            user.addPost(post);
            userRepo.save(user);
        }
        return user;
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
