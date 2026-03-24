package com.internship.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.internship.api.domain.dto.UserDTO;
import com.internship.common.exception.BizException;
import com.internship.user.domain.UserInfo;
import com.internship.user.service.UserInfoService;
import com.internship.user.mapper.UserInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 24110
* @description 针对表【user_info(用户核心信息表)】的数据库操作Service实现
* @createDate 2026-03-23 19:07:16
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

    @Override
    public UserDTO getUserByLoginId(String loginId) {

        // 1. 组装查询条件：支持用户名、手机号、邮箱三网通
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getUsername, loginId)
                .or().eq(UserInfo::getPhone, loginId)
                .or().eq(UserInfo::getEmail, loginId);

        // 2. 去数据库捞人（ServiceImpl 自带的 getOne 方法）
        UserInfo userInfo = this.getOne(wrapper);

        // 3. 核心安保防线：不再返回 Result，而是直接抛出全局业务异常！
        if (userInfo == null) {
            // 全局大管家会接住这个异常，自动转成 500/特定状态码的 JSON 给前端
            throw new BizException("用户不存在！");
        }

        if (userInfo.getStatus() == 0) {
            throw new BizException("该账号已被平台封禁，请联系客服！");
        }

        // 4. 组装快递盒（DTO）：坚决不把底层的 UserInfo 暴露出微服务
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo, userDTO);

        return userDTO;
    }
}




