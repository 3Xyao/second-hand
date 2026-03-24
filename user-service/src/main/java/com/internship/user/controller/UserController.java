package com.internship.user.controller;

import com.internship.api.domain.dto.UserDTO;
import com.internship.common.exception.BizException;
import com.internship.common.result.Result;
import com.internship.common.result.ResultCodeEnum;
import com.internship.common.utils.UserContext;
import com.internship.user.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserInfoService userInfoService;

    /**
     * 👑 核心内线接口：提供给 trade-auth 查户口用的
     */
    @GetMapping("/info/{loginId}")
    public Result<UserDTO> getUserByUsername(@PathVariable("loginId") String loginId) {

        // Controller 只管点菜（调 Service），不问后厨怎么做
        // 如果中间出错了，异常会被抛到全局拦截器，根本走不到下一行
        UserDTO userDTO = userInfoService.getUserByLoginId(loginId);

        // 完美打包返回
        return Result.success(userDTO);
    }

    @GetMapping("/test")
    public Result<String> testGatewayHeader(HttpServletRequest request) {
        // 这里的 X-User-Id 就是网关解析 JWT 后塞进去的！
        String userId = request.getHeader("X-User-Id");
        return Result.success("网关放行成功！当前登录用户ID是：" + userId);
    }

    @GetMapping("/die")
    public Result<?> testError() {
        // 故意制造一个空指针，或者直接抛出业务异常
        if (true) {
            throw new BizException(ResultCodeEnum.PARAM_ERROR);
            // 或者直接 throw new RuntimeException("故意崩给你看");
        }
        return Result.success();
    }

    @GetMapping("/me")
    public Result<Long> getMyId() {
        // 这种感觉就像是在 Service 层开了上帝视角！
        Long currentId = UserContext.getUserId();
        return Result.success(currentId);
    }
}
