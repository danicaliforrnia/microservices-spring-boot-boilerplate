package com.danicalifornia.authserver.clients;

import com.danicalifornia.authserver.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "users-service")
public interface UsersFeignClient {

    @GetMapping("/api/users/v1/users")
    User findByUsername(@RequestParam String username);
}
