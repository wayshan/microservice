package com.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class SysUser {

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("USER_NAME")
    private String userName;

    @TableField("PASSWORD")
    private String password;
}
