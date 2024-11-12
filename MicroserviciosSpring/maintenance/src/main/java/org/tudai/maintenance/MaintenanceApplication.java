package org.tudai.maintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.tudai.maintenance.feign")
public class MaintenanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaintenanceApplication.class, args);
    }

}
