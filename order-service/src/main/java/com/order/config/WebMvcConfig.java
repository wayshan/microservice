package com.order.config;

import com.common.auth.GatewayOriginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC 配置：注册拦截器。
 * <p>将 GatewayOriginInterceptor 注册到全局，拦截所有请求以校验网关签名令牌。</p>
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /** 网关来源验签拦截器 */
    @Autowired
    private GatewayOriginInterceptor gatewayOriginInterceptor;

    /**
     * 注册拦截器：
     * <ul>
     *   <li>拦截所有路径 (/**)</li>
     *   <li>排除 /actuator/** （健康检查等运维接口无需验签）</li>
     * </ul>
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(gatewayOriginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/actuator/**");
    }
}
