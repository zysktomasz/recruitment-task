package it.zysk.empik.recruitmenttask.github;

import it.zysk.empik.recruitmenttask.github.model.GithubUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "github", url = "https://api.github.com/")
// todo: add "Accept: application/vnd.github.v3+json" request header (as recommended in github api docs)
interface GithubApiClient {

    @GetMapping(value = "/users/{login}")
    GithubUser getUser(@PathVariable String login);
}
