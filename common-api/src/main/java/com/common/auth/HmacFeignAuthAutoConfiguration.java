package com.common.auth;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

/**
 * HMAC 签名 Feign 拦截器自动配置
 * <p>类级别使用 {@code @ConditionalOnClass(RequestInterceptor.class)} 守卫，
 * 仅当 Feign 在类路径中时才加载，避免对不使用 Feign 的服务产生影响。</p>
 */
@AutoConfiguration
@ConditionalOnClass(RequestInterceptor.class)
public class HmacFeignAuthAutoConfiguration {

    /**
     * 注册 Feign 请求签名拦截器 Bean。
     * <p>使服务间 Feign 调用自动携带 HMAC 签名令牌，
     * 通过目标服务的 GatewayOriginInterceptor 验签。</p>
     */
    @Bean
    public FeignGatewayHeaderInterceptor feignGatewayHeaderInterceptor() {
        return new FeignGatewayHeaderInterceptor();
    }
}
