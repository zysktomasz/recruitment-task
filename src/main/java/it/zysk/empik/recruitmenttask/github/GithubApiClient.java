package it.zysk.empik.recruitmenttask.github;

import it.zysk.empik.recruitmenttask.github.feign.GithubApiFeignConfiguration;
import it.zysk.empik.recruitmenttask.github.model.GithubUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "github", url = "https://api.github.com/", configuration = GithubApiFeignConfiguration.class)
interface GithubApiClient {

    @GetMapping(value = "/users/{login}")
    GithubUser getUser(@PathVariable String login);
}
