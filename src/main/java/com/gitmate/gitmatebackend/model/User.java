package com.gitmate.gitmatebackend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gitmate.gitmatebackend.serializer.UserSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String uniqueName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    @ToString.Exclude
    @Builder.Default
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(mappedBy = "members")
    @JsonIgnore
    @ToString.Exclude
    @Builder.Default
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Server> servers = new ArrayList<>();

    public void addPost(Post post) {
        this.posts.add(post);
    }
}



