package org.tudai.userservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tudai.scooter_trip.dto.ScooterTripDTO;

import java.util.List;

@FeignClient(name="scooter_trip")
public interface ScooterTripClient {

    @GetMapping("/scooterTrips/account/{accountId}")
    List<ScooterTripDTO>getScooterTripsByAccountId(@PathVariable Long accountId);


}
