package it.zysk.empik.recruitmenttask.github.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class GithubUserDTO {
    Long id;
    String login;
    String name;
    String type;
    String avatarUrl;
    LocalDateTime createdAt;
    Long calculations;
}
