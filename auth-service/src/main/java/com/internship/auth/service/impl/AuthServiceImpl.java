package com.internship.auth.service.impl;

import com.internship.api.client.UserFeignClient;
import com.internship.api.domain.dto.UserDTO;
import com.internship.auth.domain.dto.AdminLoginDTO;
import com.internship.auth.domain.dto.LoginReqDTO;
import com.internship.auth.domain.vo.AdminLoginVO;
import com.internship.auth.domain.vo.LoginVO;
import com.internship.auth.security.LoginUser;
import com.internship.common.utils.JwtUtils;
import com.internship.auth.service.AuthService;
import com.internship.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserFeignClient userFeignClient;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public LoginVO login(LoginReqDTO reqDTO) {
        // 1. 从 DTO 里优雅地取值
        String username = reqDTO.getUsername();
        String rawPassword = reqDTO.getPassword();

        // 2. 打内线电话给 trade-user，索要该用户的详细信息
        Result<UserDTO> feignResult = userFeignClient.getUserByLoginId(username);

        if (!feignResult.isSuccess() || feignResult.getData() == null) {
            throw new RuntimeException("用户不存在！");
        }

        UserDTO user = feignResult.getData();

        // 3. 核心安保：比对密码
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("密码错误！");
        }
        // 4. 登录成功！签发 Token
        String token = jwtUtils.generateToken(user.getId());

        // 5. 组装成高大上的 VO 返回！
        return LoginVO.builder()
                .token(token)
                // .nickname(user.getNickname()) // 以后如果有需求，直接在这里加一行代码即可！
                .build();
    }

    @Override
    public AdminLoginVO adminLogin(AdminLoginDTO adminLoginDTO) {
        // 1. 触发 Spring Security 认证流程 (这里会调用 UserDetailsService)
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authRequest);

        // 2. 认证成功！把我们那个装满数据的“快递盒”拆开
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUserDTO().getId();

        // ================= 重点：数据分流控制 =================

        // 3. 【重量级数据走 Redis】：取出那上百个权限标识，全部丢进 Redis
        Set<String> permissions = loginUser.getUserDTO().getPermissions();
        redisTemplate.opsForValue().set("auth:permissions:" + userId, permissions, 2, TimeUnit.HOURS);

        // 4. 【轻量级数据走 JWT】：只取出几个角色标识，塞进 Token
        Set<String> roles = loginUser.getUserDTO().getRoles();
        String token = jwtUtils.generateToken(userId, roles);

        // ======================================================

        // 5. 返回极其轻薄的 Token 给前端
        return token;
    }


}
