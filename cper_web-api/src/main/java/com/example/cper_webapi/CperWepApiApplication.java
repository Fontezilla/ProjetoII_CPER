package com.example.cper_webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
        "com.example.cper_core",
        "com.example.cper_webapi"
})
@EnableJpaRepositories(basePackages = "com.example.cper_core.repositories")
@EntityScan(basePackages = "com.example.cper_core.entities")
@EnableScheduling
public class CperWepApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CperWepApiApplication.class, args);
    }
}