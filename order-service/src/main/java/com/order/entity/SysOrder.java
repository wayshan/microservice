package com.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("SYS_ORDER")
public class SysOrder {

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("ORDER_NO")
    private String orderNo;

    @TableField("USER_ID")
    private Long userId;

    @TableField("ORDER_CREATE_TIME")
    private LocalDateTime orderCreateTime;
}
