package it.zysk.empik.recruitmenttask.counter.repository;

import it.zysk.empik.recruitmenttask.counter.model.LoginRequestCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterRepository extends JpaRepository<LoginRequestCount, Long> {
    Optional<LoginRequestCount> findByLogin(String login);
}
