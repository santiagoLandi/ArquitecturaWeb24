package org.tudai.scooter.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "maintenance")
public interface MaintenanceClient {

    @GetMapping("/maintenances/scooter/{scooterId}")
    List<Long> getMaintenanceIdsByScooterId(@PathVariable Long scooterId);



}
