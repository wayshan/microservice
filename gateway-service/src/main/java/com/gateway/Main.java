package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 网关服务启动类
 * <p>基于 Spring Cloud Gateway（WebFlux 响应式模型），负责：</p>
 * <ul>
 *   <li>统一外部请求入口（端口 8080）</li>
 *   <li>根据路由规则将请求转发到下游微服务</li>
 *   <li>通过全局过滤器为转发请求注入 HMAC 签名令牌</li>
 * </ul>
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
