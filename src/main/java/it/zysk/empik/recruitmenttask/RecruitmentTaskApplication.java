package it.zysk.empik.recruitmenttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RecruitmentTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentTaskApplication.class, args);
	}

}
