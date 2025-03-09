package com.gitmate.gitmatebackend.controller;

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
    public void createServer(@RequestParam("name") String name) {
        serverService.createServer(name);
        log.info("Server created: " + name);
    }

    @GetMapping("/{id}")
    public Server getServer(@PathVariable("id") Long id) {
        log.info("Server retrieved: " + id);
        return serverService.getServer(id);
    }
}