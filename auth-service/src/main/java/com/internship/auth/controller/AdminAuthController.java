package com.internship.auth.controller;

import com.internship.auth.domain.dto.AdminLoginDTO;
import com.internship.auth.domain.vo.AdminLoginVO;
import com.internship.common.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/admin")
public class AdminAuthController {

    @PostMapping("/login")
    public Result<AdminLoginVO> adminLogin(@RequestBody AdminLoginDTO  adminLoginDTO) {

    }
}
