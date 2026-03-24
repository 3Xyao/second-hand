package com.internship.auth.controller;

import com.internship.auth.domain.dto.LoginReqDTO;
import com.internship.auth.domain.vo.LoginVO;
import com.internship.auth.service.AuthService;
import com.internship.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginReqDTO reqDTO) {
        // 入参是 DTO，出参是 VO，堪称艺术！
        LoginVO loginVO = authService.login(reqDTO);
        return Result.success(loginVO);
    }


}
