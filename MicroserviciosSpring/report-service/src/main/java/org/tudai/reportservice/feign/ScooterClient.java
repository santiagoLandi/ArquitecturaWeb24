package org.tudai.reportservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.tudai.reportservice.dto.ScooterDTO;

import java.util.List;

@FeignClient(name = "scooter")
public interface ScooterClient {

    @GetMapping("/scooters/report")
    List<ScooterDTO> getScooters();




}
