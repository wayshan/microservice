package com.order.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * Feign 内部调用时自动添加网关标记头，
 * 使服务间直调也能通过 GatewayOriginInterceptor 校验。
 */
@Component
public class FeignGatewayHeaderInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("X-Gateway-Forwarded", "true");
    }
}
