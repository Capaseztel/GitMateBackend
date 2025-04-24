package com.gitmate.gitmatebackend.Mappers;

import com.gitmate.gitmatebackend.DTO.Requests.UserRequestDTO;
import com.gitmate.gitmatebackend.DTO.Responses.FullUserDTO;
import com.gitmate.gitmatebackend.Domain.User;

public class userMapper {
    public static User toUser(UserRequestDTO DTO) {
        return User.builder()
                .name(DTO.getName())
                .uniqueName(DTO.getUniqueName())
                .email(DTO.getEmail())
                .password(DTO.getPassword())
                .build();
    }

    public static FullUserDTO toDTO(User user) {
        return FullUserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .uniqueName(user.getUniqueName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
