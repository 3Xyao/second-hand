package com.internship.user.service;


import com.internship.api.domain.dto.UserDTO;
import com.internship.user.domain.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 24110
* @description 针对表【user_info(用户核心信息表)】的数据库操作Service
* @createDate 2026-03-23 19:07:16
*/
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 根据登录凭证（用户名/手机号/邮箱）获取用户信息并转换为 DTO
     * @param loginId 登录账号
     * @return UserDTO 安全的用户数据传输对象
     */
    UserDTO getUserByLoginId(String loginId);
}
