package com.eventra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {
        "com.eventra",
        "com.eventra.incident"   ,
        "com.eventra.core",
        "com.eventra.config",
        "com.eventra.notification"

})
@EnableCaching
@EnableJpaAuditing
@EnableTransactionManagement
public class EvmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvmpApplication.class, args);


    }
}
