package it.zysk.empik.recruitmenttask.rest.controller;

import it.zysk.empik.recruitmenttask.counter.service.CounterService;
import it.zysk.empik.recruitmenttask.github.GithubApiService;
import it.zysk.empik.recruitmenttask.github.dto.GithubUserDTO;
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
    private final CounterService counterService;

    @GetMapping("{login}")
    public ResponseEntity<GithubUserDTO> getGithubProfile(@PathVariable String login) {
        counterService.saveUserRequestCount(login);

        return githubApiService.getUser(login)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
