package com.kodluyoruz.weekfive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WeekFiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeekFiveApplication.class, args);
    }

}
