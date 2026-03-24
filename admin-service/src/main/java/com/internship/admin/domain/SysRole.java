package com.internship.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 角色信息表
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
public class SysRole implements Serializable {
    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称 (如: 超级管理员)
     */
    private String roleName;

    /**
     * 角色权限字符串 (如: admin)
     */
    private String roleKey;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 角色状态 (0停用 1正常)
     */
    private Integer status;

    /**
     * 删除标志 (0存在 1删除)
     */
    private Integer delFlag;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}