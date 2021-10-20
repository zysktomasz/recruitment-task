package it.zysk.empik.recruitmenttask.aop;

import it.zysk.empik.recruitmenttask.counter.service.CounterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class UpdateLoginRequestCountAspect {

    private final CounterService counterService;

    @Before("execution(* it.zysk.empik.recruitmenttask.rest.controller.GithubUserController.getGithubProfile(..)) " +
            "&& args(login)")
    public void saveUserRequestCountAspect(JoinPoint joinPoint, String login) {
        log.info("running UpdateLoginRequestCountAspect for login: '{}'", login);
        counterService.saveUserRequestCount(login);
    }
}
