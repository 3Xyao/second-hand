package com.internship.api.domain.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.Set;

/**
 * 专门用于身份认证和权限校验的数据传输对象
 */
@Data
public class SysUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    // --- 用户基础核心信息 ---
    private Long id;
    private String username;
    private String password; // 必须带上加密后的密码，用于认证
    private Integer status;  // 状态（0禁 1正常）
    private Integer delFlag; // 删除标志

    // --- 权限核心信息 ---
    // 在认证阶段，我们通常把角色和权限一次性查出来给 Auth 服务
    private Set<String> roles;       // 角色标识集合（如：admin, user）
    private Set<String> permissions; // 权限标识集合（如：sys:user:add）
}