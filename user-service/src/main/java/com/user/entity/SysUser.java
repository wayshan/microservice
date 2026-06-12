package com.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 系统用户实体类，对应达梦数据库 SYS_USER 表。
 * <p>使用 MyBatis-Plus 注解映射表字段。</p>
 */
@Data
public class SysUser {

    /** 用户主键 ID（数据库自增） */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /** 用户名 */
    @TableField("USER_NAME")
    private String userName;

    /** 密码（仅数据库层存储，对外接口不暴露） */
    @TableField("PASSWORD")
    private String password;
}
