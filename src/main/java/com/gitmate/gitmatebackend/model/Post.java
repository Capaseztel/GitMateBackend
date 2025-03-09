package com.gitmate.gitmatebackend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gitmate.gitmatebackend.serializer.PostSerializer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id", scope = Post.class
//)
//@JsonSerialize(using = PostSerializer.class)

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @Builder.Default
    @JsonIgnore
    @ToString.Exclude
    private Post parent = null;

    private String content;

    @JoinColumn(nullable = false)
    @ManyToOne
    @Builder.Default
    private User author = null;

    @OneToMany()
    @Builder.Default
    private List<Post> comments = new ArrayList<>();

    public void addComment(Post comment) {
        this.comments.add(comment);
    }
}
