package com.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户服务启动类
 * <p>提供用户相关的 REST API（CRUD），被 order-service 通过 Feign 调用。</p>
 * <ul>
 *   <li>{@code @MapperScan} - 扫描 MyBatis-Plus Mapper 接口</li>
 * </ul>
 */
@SpringBootApplication
@MapperScan("com.user.mapper")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
