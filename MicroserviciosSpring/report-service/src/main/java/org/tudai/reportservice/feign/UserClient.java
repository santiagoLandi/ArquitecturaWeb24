package org.tudai.reportservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @PatchMapping("/accounts/deactivate/{accountId}")
    void deactivateAccount(@PathVariable("accountId") Long accountId);

    @PatchMapping("/accounts/activate/{accountId}")
    void activateAccount(@PathVariable("accountId") Long accountId);
}
