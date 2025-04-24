package com.gitmate.gitmatebackend.Service;

import com.gitmate.gitmatebackend.DTO.Requests.LoginRequest;
import com.gitmate.gitmatebackend.Domain.Server;
import com.gitmate.gitmatebackend.Repositories.ServerRepo;
import com.gitmate.gitmatebackend.Repositories.UserRepo;
import com.gitmate.gitmatebackend.Domain.Post;
import com.gitmate.gitmatebackend.Domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    UserRepo userRepo;
    @Autowired
    ServerRepo serverRepo;

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public User addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User loginUser(LoginRequest loginRequest) {
        log.info("Logging user {}: {}", loginRequest.getUniqueName(), loginRequest.getPassword());
        return encoder.matches(loginRequest.getPassword(), userRepo.getUserByUniqueName(loginRequest.getUniqueName()).getPassword()) ? userRepo.getUserByUniqueName(loginRequest.getUniqueName()) : null;
    }

    public User updateUser(Long id, User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        return userRepo.save(user);
    }

    public void addPost(Post post) {
        User user = userRepo.findById(post.getAuthor().getId()).orElse(null);
        if (user != null) {
            user.addPost(post);
            userRepo.save(user);
        }
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public boolean joinServer(Long serverId, Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        Server server = serverRepo.findById(serverId).orElse(null);
        if (user != null && server != null) {
            user.addServer(server);
            server.addMember(user);
            serverRepo.save(server);
            userRepo.save(user);
            return true;
        }
        return false;
    }

    public List<Server> getUserServers(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return user.getServers();
        }
        return null;
    }
}
