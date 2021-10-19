package it.zysk.empik.recruitmenttask.github;

import it.zysk.empik.recruitmenttask.github.dto.GithubUserDTO;
import it.zysk.empik.recruitmenttask.github.mapper.GithubMapper;
import it.zysk.empik.recruitmenttask.github.model.GithubUser;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GithubApiServiceTest {

    @Mock
    private GithubApiClient githubApiClient;

    @Mock
    private GithubMapper githubMapper;

    @InjectMocks
    private GithubApiService githubApiService;

    @Nested
    class GetUserTest {

        @ParameterizedTest
        @NullAndEmptySource
        void should_ThrowIllegalArgumentException_When_LoginParameterIsInvalid(String login) {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> githubApiService.getUser(login));

            assertEquals("'login' parameter cannot be empty", exception.getMessage());
        }

        @Test
        void should_ReturnGithubUserDTO_When_GithubUserExists() {
            String login = "someLogin";
            Long id = 1L;
            GithubUser githubUser = GithubUser.builder().id(id).login(login).build();
            GithubUserDTO githubUserDTO = GithubUserDTO.builder().id(id).login(login).build();
            doReturn(githubUser).when(githubApiClient).getUser(login);
            doReturn(githubUserDTO).when(githubMapper).mapGithubUserToDTO(githubUser);

            GithubUserDTO responseUser = githubApiService.getUser(login);

            assertEquals(githubUserDTO, responseUser);
            verify(githubApiClient).getUser(login);
        }
    }
}