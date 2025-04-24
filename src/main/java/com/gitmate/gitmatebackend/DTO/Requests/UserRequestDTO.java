package com.gitmate.gitmatebackend.DTO.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
    private Long id;

    private String name;

    private String uniqueName;

    private String email;

    private String password;
}
