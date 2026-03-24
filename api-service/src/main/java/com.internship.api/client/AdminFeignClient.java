package com.internship.api.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("admin-service")
public interface AdminFeignClient {

}
