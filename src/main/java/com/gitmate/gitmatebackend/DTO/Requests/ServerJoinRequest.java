package com.gitmate.gitmatebackend.DTO.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServerJoinRequest {
    Long serverId;
    Long userId;
}
