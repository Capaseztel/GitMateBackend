package com.gitmate.gitmatebackend.Repositories;

import com.gitmate.gitmatebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User getUserByUniqueNameAndPassword(String un, String pw);
}