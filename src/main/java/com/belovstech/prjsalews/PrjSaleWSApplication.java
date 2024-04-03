package com.belovstech.prjsalews;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PrjSaleWSApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrjSaleWSApplication.class, args);
    }
}
