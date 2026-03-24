package com.internship.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.internship.admin.domain.SysUser;
import com.internship.admin.service.SysUserService;
import com.internship.admin.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 24110
* @description 针对表【sys_user(用户用户信息表)】的数据库操作Service实现
* @createDate 2026-03-24 15:47:02
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




