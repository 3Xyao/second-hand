package com.internship.auth.domain.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder // 开启建造者模式，方便后面优雅地赋值
public class LoginVO {

    // 核心凭证
    private String token;

    // 预留的无限扩展位（现在不一定用，但位置必须占好）：
    // private String nickname;    // 用户昵称（前端可以在右上角直接显示，不用再发一次请求）
    // private String avatarUrl;   // 头像地址
    // private List<String> roles; // 角色权限列表（前端用来控制菜单显示）
}