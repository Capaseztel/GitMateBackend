package com.gitmate.gitmatebackend.Repositories;

import com.gitmate.gitmatebackend.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User getUserByUniqueName(String un);

    User findUserByUniqueName(String username);
}