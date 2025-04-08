package com.springproject.medihubweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.springproject"})
@EnableJpaRepositories(basePackages = "com.springproject.medihubdata.repository")
@EntityScan(basePackages = "com.springproject.medihubdata.model")
public class MediHubWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediHubWebApplication.class, args);
    }

}
