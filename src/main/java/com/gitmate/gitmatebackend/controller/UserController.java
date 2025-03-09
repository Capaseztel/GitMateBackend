package com.gitmate.gitmatebackend.controller;

import com.gitmate.gitmatebackend.model.User;
import com.gitmate.gitmatebackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/v1/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping({"", "/"})
    public List<User> getUsers() {
        log.info("Getting all users");
        return this.userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        log.info("Getting user by id");
        return this.userService.getUserById(id);
    }

    @PostMapping({"", "/"})
    public User addUser(@RequestBody User user) {
        log.info("Adding user");
        user.setId(null);
        System.out.println(user
        );
        return this.userService.addUser(user);
    }

    @PostMapping("/login")
    public User AuthUser(@RequestParam("uniqueName") String uniqueName,
                         @RequestParam("password") String password) {
        log.info("Logging in user");
        return this.userService.loginUser(uniqueName, password);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        log.info("Updating user by id");
        return this.userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        log.info("Deleting user by id");
        this.userService.deleteUser(id);
    }

}