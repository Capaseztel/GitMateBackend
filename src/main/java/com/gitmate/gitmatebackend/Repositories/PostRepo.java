package com.gitmate.gitmatebackend.Repositories;

import com.gitmate.gitmatebackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

}
