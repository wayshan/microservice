package com.order.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysOrderVO {

    private Long id;

    private String orderNo;

    private Long userId;

    private LocalDateTime orderCreateTime;

    /**
     * 关联查询的用户名
     */
    private String userName;
}
