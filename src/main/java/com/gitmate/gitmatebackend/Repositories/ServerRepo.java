package com.gitmate.gitmatebackend.Repositories;

import com.gitmate.gitmatebackend.Domain.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepo extends JpaRepository<Server, Long> {

    Server findByName(String name);
}
