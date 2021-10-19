package it.zysk.empik.recruitmenttask.github;

import it.zysk.empik.recruitmenttask.github.dto.GithubUserDTO;
import it.zysk.empik.recruitmenttask.github.mapper.GithubMapper;
import it.zysk.empik.recruitmenttask.github.model.GithubUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubApiService {

    private final GithubApiClient githubApiClient;
    private final GithubMapper githubMapper;

    public GithubUserDTO getUser(String login) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("'login' parameter cannot be empty");
        }

        // todo: handle response from GithubApi when Github user for provided 'login' does not exist

        GithubUser user = githubApiClient.getUser(login);
        return githubMapper.mapGithubUserToDTO(user);
    }
}
