package it.zysk.empik.recruitmenttask.github.mapper;

import it.zysk.empik.recruitmenttask.github.dto.GithubUserDTO;
import it.zysk.empik.recruitmenttask.github.model.GithubUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GithubMapperTest {

    private final GithubMapper githubMapper = Mappers.getMapper(GithubMapper.class);

    public static int[][] testParameters() {
        return new int[][]{{2, 2, 12}, {6, 8, 10}, {12, 7, 0}, {6, 32, 34}};
    }

    @ParameterizedTest
    @MethodSource(value = "testParameters")
    void should_ReturnCorrectCalculationsResult_When_MappingGithubUserToDTO(int[] params) {
        int followers = params[0];
        int publicRepos = params[1];
        int expectedResult = params[2];
        GithubUser githubUser = GithubUser.builder().followers((long) followers).publicRepos((long) publicRepos).build();

        GithubUserDTO resultDTO = githubMapper.mapGithubUserToDTO(githubUser);

        assertEquals(expectedResult, resultDTO.getCalculations());
    }

    @Test
    void should_ReturnNull_When_DividingByZero() {
        GithubUser githubUser = GithubUser.builder().followers(0L).publicRepos(123L).build();

        GithubUserDTO resultDTO = githubMapper.mapGithubUserToDTO(githubUser);

        assertNull(resultDTO.getCalculations());
    }

    @Test
    void should_MapAllFieldsProperly_When_MappingValidGithubUser() {
        Long id = 123L;
        String login = "batman";
        String name = "name";
        String avatarUrl = "http://example.com";
        String type = "User";
        LocalDateTime createdAt = LocalDateTime.now();
        GithubUser githubUser = GithubUser.builder()
                .id(id).login(login).name(name).avatarUrl(avatarUrl).type(type).createdAt(createdAt)
                .followers(2L).publicRepos(2L).build();

        GithubUserDTO expectedDTO = GithubUserDTO.builder()
                .id(id).login(login).name(name).avatarUrl(avatarUrl).type(type).createdAt(createdAt)
                .calculations(12L)
                .build();

        GithubUserDTO resultDTO = githubMapper.mapGithubUserToDTO(githubUser);

        assertEquals(expectedDTO, resultDTO);
    }
}