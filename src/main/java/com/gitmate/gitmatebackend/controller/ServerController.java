package com.gitmate.gitmatebackend.controller;

import com.gitmate.gitmatebackend.model.Channel;
import com.gitmate.gitmatebackend.model.Server;
import com.gitmate.gitmatebackend.service.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/api/server")
public class ServerController {
    @Autowired
    private ServerService serverService;

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

    @GetMapping("/channel/{id}")
    public Channel getChannel(@PathVariable("id") Long id) {
        return serverService.getChannel(id);
    }

    @PostMapping("/{id}")
    public Channel createChannel(@PathVariable("id") Long id, @RequestParam("name") String name) {
        return serverService.addChannel(name, serverService.getServer(id));
    }
}