package com.internship.auth.security;

import com.internship.api.domain.dto.SysUserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    // 核心：包装 DTO 对象
    private SysUserDTO userDTO;

    // 构造方法传入 DTO
    public LoginUser(SysUserDTO userDTO) {
        this.userDTO = userDTO;
    }

    // --- 权限转换核心逻辑 ---
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userDTO.getPermissions() == null || userDTO.getPermissions().isEmpty()) {
            return Collections.emptyList();
        }
        // 将 DTO 里的字符串权限集合，转换为 Spring Security 认识的 GrantedAuthority
        return userDTO.getPermissions().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    // --- 获取账号密码 ---
    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDTO.getUsername();
    }

    // --- 状态校验逻辑 ---
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 状态为 1 表示正常，0 表示被封禁
        return userDTO.getStatus() != null && userDTO.getStatus() == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 删除标志为 0 表示存在，1 表示已删除
        return userDTO.getDelFlag() != null && userDTO.getDelFlag() == 0;
    }
}
