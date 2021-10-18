package it.zysk.empik.recruitmenttask.github;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "github", url = "https://api.github.com/")
public interface GithubApiClient {

    @GetMapping(value = "/users/{login}")
    Object getUser(@PathVariable String login);
}
