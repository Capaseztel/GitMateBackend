package com.gitmate.gitmatebackend.Repositories;

import com.gitmate.gitmatebackend.Domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepo extends JpaRepository<Channel, Long> {

}
