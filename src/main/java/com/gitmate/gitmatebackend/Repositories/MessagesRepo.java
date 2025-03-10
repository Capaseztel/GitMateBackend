package com.gitmate.gitmatebackend.Repositories;

import com.gitmate.gitmatebackend.Domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepo extends JpaRepository<Message, Long> {

}
