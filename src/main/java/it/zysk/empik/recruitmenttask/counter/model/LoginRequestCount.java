package it.zysk.empik.recruitmenttask.counter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOGIN_REQUEST_COUNT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginRequestCount {

    @Id
    @Column(name = "LOGIN", unique = true)
    private String login;

    @Column(name = "REQUEST_COUNT")
    @Builder.Default
    private Long requestCount = 1L;
}
