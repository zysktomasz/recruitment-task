package it.zysk.empik.recruitmenttask.github.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
@JsonDeserialize(builder = GithubUserDTO.GithubUserDTOBuilder.class)
public class GithubUserDTO {
    Long id;
    String login;
    String name;
    String type;
    String avatarUrl;
    LocalDateTime createdAt;
    Long calculations;

    @JsonPOJOBuilder(withPrefix = "")
    public static class GithubUserDTOBuilder {

    }
}
