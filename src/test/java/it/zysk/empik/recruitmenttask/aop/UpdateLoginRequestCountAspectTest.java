package it.zysk.empik.recruitmenttask.aop;

import it.zysk.empik.recruitmenttask.counter.service.CounterService;
import it.zysk.empik.recruitmenttask.github.GithubApiService;
import it.zysk.empik.recruitmenttask.rest.controller.GithubUserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = {
        GithubUserController.class,
        AnnotationAwareAspectJAutoProxyCreator.class,
        UpdateLoginRequestCountAspect.class
})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class UpdateLoginRequestCountAspectTest {

    @MockBean
    private GithubApiService githubApiService;

    @MockBean
    private CounterService counterService;

    @Autowired
    private MockMvc mvc;

    @Test
    void should_InvokeCounterServiceAspectWithValidLogin_When_WatchedControllerMethodsIsCalled() throws Exception {
        String login = "someLogin";

        mvc.perform(get("/users/" + login)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        verify(counterService).saveUserRequestCount(login);
    }
}
