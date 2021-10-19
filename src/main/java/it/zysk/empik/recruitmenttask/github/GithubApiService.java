package it.zysk.empik.recruitmenttask.github;

import feign.FeignException;
import it.zysk.empik.recruitmenttask.github.dto.GithubUserDTO;
import it.zysk.empik.recruitmenttask.github.mapper.GithubMapper;
import it.zysk.empik.recruitmenttask.github.model.GithubUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubApiService {

    private final GithubApiClient githubApiClient;
    private final GithubMapper githubMapper;

    public Optional<GithubUserDTO> getUser(String login) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("'login' parameter cannot be empty");
        }

        GithubUser user;
        try {
            user = githubApiClient.getUser(login);
        } catch (FeignException.NotFound notFound) {
            log.warn("Unable to find Github User with login: '{}'", login);
            return Optional.empty();
        }

        return Optional.of(githubMapper.mapGithubUserToDTO(user));
    }
}
