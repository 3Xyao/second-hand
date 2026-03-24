package com.internship.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户核心信息表
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
public class UserInfo implements Serializable {
    /**
     * 用户主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 登录账号(唯一)
     */
    private String username;

    /**
     * 登录密码(BCrypt密文)
     */
    private String password;

    /**
     * 手机号(唯一，预留手机登录)
     */
    private String phone;

    /**
     * 电子邮箱(唯一，预留邮箱登录)
     */
    private String email;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 头像URL
     */
    private String avatarUrl;

    /**
     * 性别：0-未知，1-男，2-女
     */
    private Integer gender;

    /**
     * 个性签名
     */
    private String bio;

    /**
     * 信用积分(满分100，违规扣分)
     */
    private Integer creditScore;

    /**
     * 账号状态：1-正常，0-封禁锁死
     */
    private Integer status;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除：0-未删除，1-已删除
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}