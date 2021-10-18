package it.zysk.empik.recruitmenttask.rest.controller;

import it.zysk.empik.recruitmenttask.github.GithubApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class GithubUserController {

    private final GithubApiClient githubApiClient;

    @GetMapping("{login}")
    public Object getGithubProfile(@PathVariable String login) {
        Object user = githubApiClient.getUser(login);
        return user;
    }
}