package it.zysk.empik.recruitmenttask.github.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class GithubApiFeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("accept", "application/vnd.github.v3+json");
        };
    }
}
