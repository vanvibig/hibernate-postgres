package com.example.hibernatepostgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HibernatePostgresApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernatePostgresApplication.class, args);
    }

}
