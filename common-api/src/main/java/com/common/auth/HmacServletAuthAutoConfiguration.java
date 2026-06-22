package com.common.auth;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * HMAC 签名验签自动配置（仅 Servlet Web 环境）
 * <p>类级别使用 {@code @ConditionalOnClass(HandlerInterceptor.class)} 守卫，
 * 当网关等 WebFlux 环境没有 spring-webmvc 时，Spring 直接跳过整个配置类，
 * 不会尝试加载其方法签名，从而避免 {@code NoClassDefFoundError}。</p>
 */
@AutoConfiguration
@ConditionalOnClass(HandlerInterceptor.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class HmacServletAuthAutoConfiguration {

    /**
     * 注册网关来源验签拦截器 Bean。
     * <p>仅在 Servlet Web 应用（如 order-service、user-service）中生效。</p>
     */
    @Bean
    public GatewayOriginInterceptor gatewayOriginInterceptor() {
        return new GatewayOriginInterceptor();
    }
}
