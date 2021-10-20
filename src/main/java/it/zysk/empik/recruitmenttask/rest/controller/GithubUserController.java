package it.zysk.empik.recruitmenttask.rest.controller;

import io.swagger.annotations.ApiOperation;
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

    private final GithubApiService githubApiService;

    @ApiOperation(value = "Get Github User details")
    @GetMapping("{login}")
    public ResponseEntity<GithubUserDTO> getGithubProfile(@PathVariable String login) {
        return githubApiService.getUser(login)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
