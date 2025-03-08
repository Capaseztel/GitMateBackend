package com.gitmate.gitmatebackend.model.DTO;


import com.gitmate.gitmatebackend.model.Post;
import com.gitmate.gitmatebackend.model.Server;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserServersDTO {

    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String uniqueName;

    private String email;

    private String password;

    private List<Post> posts = new ArrayList<>();

    private List<Server> servers = new ArrayList<>();

}
