package com.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统订单实体类，对应达梦数据库 SYS_ORDER 表。
 * <p>使用 MyBatis-Plus 注解映射表字段。</p>
 */
@Data
@TableName("SYS_ORDER")
public class SysOrder {

    /** 订单主键 ID（数据库自增） */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /** 订单编号 */
    @TableField("ORDER_NO")
    private String orderNo;

    /** 关联用户 ID（对应 SYS_USER 表的 ID） */
    @TableField("USER_ID")
    private Long userId;

    /** 订单创建时间 */
    @TableField("ORDER_CREATE_TIME")
    private LocalDateTime orderCreateTime;
}
