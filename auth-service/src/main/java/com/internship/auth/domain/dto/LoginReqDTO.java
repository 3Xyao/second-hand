package com.internship.auth.domain.dto;

import lombok.Data;

@Data
public class LoginReqDTO {

    // @NotBlank(message = "账号不能为空")
    private String username;

    // @NotBlank(message = "密码不能为空")
    private String password;

    // 预留扩展：以后可以随时加参数，不用改方法的签名！
    // private String captchaCode;
    // private String loginType;
}