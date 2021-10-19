package it.zysk.empik.recruitmenttask.counter.service;

import it.zysk.empik.recruitmenttask.counter.model.LoginRequestCount;
import it.zysk.empik.recruitmenttask.counter.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CounterService {
    private final CounterRepository counterRepository;

    /**
     * Stores number of requests made for each login. Creates new record if it does not exist yet.
     * Otherwise, increases count by 1 for existing record by 'login' param.
     *
     * @param login value used to get Github User by
     */
    public void saveUserRequestCount(String login) {
        LoginRequestCount entity = counterRepository.findByLogin(login)
                .map(updateRequestCountByOne())
                .orElse(LoginRequestCount.builder().login(login).build());

        counterRepository.save(entity);
    }

    private Function<LoginRequestCount, LoginRequestCount> updateRequestCountByOne() {
        return loginRequestCount -> {
            loginRequestCount.setRequestCount(loginRequestCount.getRequestCount() + 1);
            return loginRequestCount;
        };
    }
}
