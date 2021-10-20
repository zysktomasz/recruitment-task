package it.zysk.empik.recruitmenttask.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.zysk.empik.recruitmenttask.github.GithubApiService;
import it.zysk.empik.recruitmenttask.github.dto.GithubUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@WebMvcTest(GithubUserController.class)
@Import(AnnotationAwareAspectJAutoProxyCreator.class)
class GithubUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GithubApiService githubApiService;

    @Test
    void should_ReturnNoFoundResponse_When_UserIsNotFound() throws Exception {
        String login = "someLogin";

        doReturn(Optional.empty()).when(githubApiService).getUser(login);

        MockHttpServletResponse response = mvc.perform(get("/users/" + login)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void should_ReturnGithubUserDTO_When_UserIsFound() throws Exception {
        String login = "someLogin";
        GithubUserDTO githubUserDTO = GithubUserDTO.builder().id(1L).login(login).build();
        ObjectMapper objectMapper = new ObjectMapper();

        doReturn(Optional.of(githubUserDTO)).when(githubApiService).getUser(login);

        MockHttpServletResponse response = mvc.perform(get("/users/" + login)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        GithubUserDTO responseUser = objectMapper.readValue(response.getContentAsByteArray(), GithubUserDTO.class);
        assertEquals(githubUserDTO, responseUser);
    }
}