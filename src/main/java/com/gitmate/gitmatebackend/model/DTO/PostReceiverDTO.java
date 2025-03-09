package com.gitmate.gitmatebackend.model.DTO;

import com.gitmate.gitmatebackend.model.Post;
import com.gitmate.gitmatebackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReceiverDTO {

    private Long id;

    private String title;

    private Post parent = null;

    private String content;

    private User author;

    private List<Post> comments = new ArrayList<>();
}
