package org.tudai.scooter_trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.tudai.scooter_trip.feign")
public class ScooterTripApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScooterTripApplication.class, args);
    }

}
