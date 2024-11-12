package org.tudai.scooter_trip.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tudai.userservice.dto.AccountDTO;

@FeignClient(name = "user-service")
public interface AccountClient {

    @GetMapping("/accounts/{accountId}")
    AccountDTO getAccountById(@PathVariable("accountId") Long accountId);
}
