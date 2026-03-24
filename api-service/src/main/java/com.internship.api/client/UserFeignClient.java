package com.internship.api.client;

import com.internship.api.domain.dto.UserDTO;
import com.internship.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service")
public interface UserFeignClient {

    @GetMapping("/user/info/{loginId}")
    Result<UserDTO> getUserByLoginId(@PathVariable("loginId") String loginId);
}
