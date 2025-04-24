package com.gitmate.gitmatebackend.Controller;

import com.gitmate.gitmatebackend.DTO.Requests.LoginRequest;
import com.gitmate.gitmatebackend.DTO.Requests.ServerJoinRequest;
import com.gitmate.gitmatebackend.DTO.Requests.UserRequestDTO;
import com.gitmate.gitmatebackend.DTO.Responses.FullUserDTO;
import com.gitmate.gitmatebackend.DTO.Responses.ServerListableDTO;
import com.gitmate.gitmatebackend.Mappers.userMapper;
import com.gitmate.gitmatebackend.Domain.User;
import com.gitmate.gitmatebackend.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/v1/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping({"", "/"})
    public List<FullUserDTO> getUsers() {
        log.info("Getting all users");
        List<User> users = userService.getUsers();
        List<FullUserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(userMapper.toDTO(user));
        }
        return userDTOs;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        log.info("Getting user by id");
        return this.userService.getUserById(id);
    }

    @PostMapping({"", "/"})
    public User addUser(@RequestBody UserRequestDTO DTO) {
        log.info("Adding user");
        User user = userMapper.toUser(DTO);
        user.setId(null);
        System.out.println(user
        );
        return this.userService.addUser(user);
    }

    @PostMapping("/join")
    public String joinServer(@RequestBody ServerJoinRequest serverJoinRequest) {
        log.info("Joining user to server");
        return this.userService.joinServer(serverJoinRequest.getServerId(), serverJoinRequest.getUserId()) ? "Joined server" : "Failed to join server";
    }

    @GetMapping("/servers/{userId}")
    public List<ServerListableDTO> getUsersByServer(@PathVariable("userId") Long userId) {
        log.info("Getting users by server");
        List<ServerListableDTO> servers = new ArrayList<>();
        this.userService.getUserServers(userId).forEach(server -> servers.add(ServerListableDTO.builder().id(server.getId()).name(server.getName()).build()));
        return servers;
    }

    @PostMapping("/login")
    public String AuthUser(@RequestBody LoginRequest loginRequest) {
        log.info("Logging in user");
        if (this.userService.loginUser(loginRequest) != null) {
            return "loged in with userID: " + this.userService.loginUser(loginRequest).getId();
        } else {
            return "Invalid Credentials :c";
        }
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody UserRequestDTO DTO) {
        log.info("Updating user by id");
        User user = userMapper.toUser(DTO);
        return this.userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        log.info("Deleting user by id");
        this.userService.deleteUser(id);
    }

}