package it.zysk.empik.recruitmenttask.rest.controller;

import it.zysk.empik.recruitmenttask.github.GithubApiService;
import it.zysk.empik.recruitmenttask.github.model.GithubUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class GithubUserController {

    // todo: add exception handler

    private final GithubApiService githubApiService;

    @GetMapping("{login}")
    public ResponseEntity<GithubUser> getGithubProfile(@PathVariable String login) {
        GithubUser user = githubApiService.getUser(login);
        return ResponseEntity.ok(user);
    }
}
