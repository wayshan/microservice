package com.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单服务启动类
 * <p>提供订单相关的 REST API，并通过 Feign 调用其他微服务（如 user-service）。</p>
 * <ul>
 *   <li>{@code @MapperScan} - 扫描 MyBatis-Plus Mapper 接口</li>
 *   <li>{@code @EnableFeignClients} - 启用 Feign 客户端，扫描 common-api 中的 Feign 接口</li>
 * </ul>
 */
@SpringBootApplication
@MapperScan("com.order.mapper")
@EnableFeignClients(basePackages = {"com.common.feign"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
