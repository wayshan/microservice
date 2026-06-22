package com.common.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

/**
 * Feign 内部调用时自动生成 HMAC 签名令牌。
 * <p>放在 common-api 中供所有使用 Feign 的微服务复用，
 * 使服务间直调也能通过 GatewayOriginInterceptor 验签。</p>
 */
public class FeignGatewayHeaderInterceptor implements RequestInterceptor {

    /** HMAC 签名密钥，从配置文件读取 */
    @Value("${gateway.auth.secret-key}")
    private String secretKey;

    /**
     * Feign 请求拦截，自动注入 HMAC 签名令牌。
     * <p>确保服务间内部调用也能通过目标服务的 GatewayOriginInterceptor 验签。</p>
     */
    @Override
    public void apply(RequestTemplate template) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String token = HmacUtils.generateToken(timestamp, secretKey);
        template.header("X-Gateway-Token", token);
    }
}
