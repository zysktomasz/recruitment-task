package it.zysk.empik.recruitmenttask.github;

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
        void should_ReturnGithubUser_When_GithubUserExists() {
            String someLogin = "someLogin";
            GithubUser githubUser = GithubUser.builder().id(1L).login(someLogin).build();
            doReturn(githubUser).when(githubApiClient).getUser(someLogin);

            GithubUser responseUser = githubApiService.getUser(someLogin);

            assertEquals(githubUser, responseUser);
            verify(githubApiClient).getUser(someLogin);
        }
    }
}