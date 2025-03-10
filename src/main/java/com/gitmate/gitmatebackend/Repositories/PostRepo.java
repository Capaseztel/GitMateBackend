package com.gitmate.gitmatebackend.Repositories;

import com.gitmate.gitmatebackend.Domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;


@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    @Query(value = "SELECT P FROM Post P",
            countQuery = "select count(*) from Post")
    public Page<Post> findAllPageable(Pageable pageable);
}
