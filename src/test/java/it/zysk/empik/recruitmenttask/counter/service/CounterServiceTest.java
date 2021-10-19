package it.zysk.empik.recruitmenttask.counter.service;

import it.zysk.empik.recruitmenttask.counter.model.LoginRequestCount;
import it.zysk.empik.recruitmenttask.counter.repository.CounterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CounterServiceTest {

    @Mock
    private CounterRepository counterRepository;

    @InjectMocks
    private CounterService counterService;

    @Captor
    private ArgumentCaptor<LoginRequestCount> loginRequestCountArgumentCaptor;

    @Test
    void should_createNewEntityForProvidedLogin_When_EntityDoesNotExist() {
        String login = "someLogin";
        doReturn(Optional.empty()).when(counterRepository).findByLogin(login);

        counterService.saveUserRequestCount(login);

        verify(counterRepository).save(loginRequestCountArgumentCaptor.capture());
        LoginRequestCount capturedEntity = loginRequestCountArgumentCaptor.getValue();
        assertEquals(login, capturedEntity.getLogin());
        assertEquals(1L, capturedEntity.getRequestCount());
    }

    @Test
    void should_UpdateRequestCountBy1_When_EntityExists() {
        String login = "someLogin";
        long requestCount = 10L;
        LoginRequestCount existingEntity = LoginRequestCount.builder()
                .login(login)
                .requestCount(requestCount)
                .build();
        doReturn(Optional.of(existingEntity)).when(counterRepository).findByLogin(login);

        counterService.saveUserRequestCount(login);

        verify(counterRepository).save(loginRequestCountArgumentCaptor.capture());
        LoginRequestCount capturedEntity = loginRequestCountArgumentCaptor.getValue();
        assertEquals(login, capturedEntity.getLogin());
        assertEquals(requestCount + 1, capturedEntity.getRequestCount());
    }
}