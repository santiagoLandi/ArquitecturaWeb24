package org.tudai.scooter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.tudai.scooter.feign")
public class ScooterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScooterApplication.class, args);
    }

}
