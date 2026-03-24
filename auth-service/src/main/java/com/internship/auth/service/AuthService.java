package com.internship.auth.service;

import com.internship.auth.domain.dto.AdminLoginDTO;
import com.internship.auth.domain.dto.LoginReqDTO;
import com.internship.auth.domain.vo.AdminLoginVO;
import com.internship.auth.domain.vo.LoginVO;

public interface AuthService {
    /**
     * 登录并返回视图对象 VO
     */
    LoginVO login(LoginReqDTO reqDTO);

    AdminLoginVO adminLogin(AdminLoginDTO adminLoginDTO);
}
