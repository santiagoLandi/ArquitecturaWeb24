package org.tudai.reportservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="scooter-trip")
public interface ScooterTripClient {

    @GetMapping("/scooterTrips/countByScooterAndYear")
    Long countScooterTripByScooterAndYear(@RequestParam Long scooterId, @RequestParam int year);

}
