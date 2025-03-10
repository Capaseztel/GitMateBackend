package com.gitmate.gitmatebackend.Controller;

import com.gitmate.gitmatebackend.DTO.Requests.MessageRequest;
import com.gitmate.gitmatebackend.Domain.Channel;
import com.gitmate.gitmatebackend.Domain.Message;
import com.gitmate.gitmatebackend.Domain.Server;
import com.gitmate.gitmatebackend.Service.ServerService;
import com.gitmate.gitmatebackend.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/api/server")
public class ServerController {
    @Autowired
    private ServerService serverService;
    @Autowired
    private UserService userService;

    @PostMapping({"", "/"})
    public Server createServer(@RequestParam("name") String name) {
        log.info("Server created: {}", name);
        return serverService.createServer(name);
    }

    @GetMapping("/{id}")
    public Server getServer(@PathVariable("id") Long id) {
        log.info("Server retrieved: {}", id);
        return serverService.getServer(id);
    }

    @GetMapping("/{serverID}/{id}")
    public Channel getChannel(@PathVariable("id") Long id, @PathVariable Long serverID) {
        if (serverService.getServer(serverID).getChannels().contains(serverService.getChannel(id))) {
            return serverService.getChannel(id);
        }
        return null;
    }

    @PostMapping("/{id}")
    public Channel createChannel(@PathVariable("id") Long id, @RequestParam("name") String name) {
        return serverService.addChannel(name, serverService.getServer(id));
    }

    @PostMapping("/{serverID}/{id}")
    public Message sendMessage(@PathVariable("id") Long id, @PathVariable Long serverID, @RequestBody MessageRequest messageRequest) {
        if (serverService.getServer(serverID).getChannels().contains(serverService.getChannel(id))) {
            return serverService.sendMessage(userService.getUserById(messageRequest.getSender()), serverService.getChannel(id), messageRequest.getContent());
        } else {
            return null;
        }
    }
}