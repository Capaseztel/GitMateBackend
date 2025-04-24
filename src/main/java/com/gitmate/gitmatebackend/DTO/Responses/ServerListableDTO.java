package com.gitmate.gitmatebackend.DTO.Responses;

import com.gitmate.gitmatebackend.Domain.Channel;
import com.gitmate.gitmatebackend.Domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerListableDTO {

    private Long id;

    private String name;

}
