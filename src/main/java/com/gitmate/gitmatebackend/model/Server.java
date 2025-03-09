package com.gitmate.gitmatebackend.model;
import com.gitmate.gitmatebackend.Repositories.ChannelRepo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Server {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @Builder.Default
    private List<User> members = new ArrayList<>();

    @OneToMany
    @Builder.Default
    private List<Channel> channels = new ArrayList<>();

    public void addChannel(Channel channel) {
        channels.add(channel);
    }
}
