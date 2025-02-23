package br.com.techchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class TechChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechChallengeApplication.class, args);
    }
}
