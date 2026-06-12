package com.order.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Feign 内部调用时自动生成 HMAC 签名令牌，
 * 使服务间直调也能通过 GatewayOriginInterceptor 验签。
 */
@Component
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
        String signature = sign(timestamp, secretKey);
        template.header("X-Gateway-Token", timestamp + "." + signature);
    }

    /**
     * 使用 HMAC-SHA256 算法签名。
     *
     * @param data 待签名数据
     * @param key  签名密钥
     * @return Base64 编码的签名
     */
    private static String sign(String data, String key) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("HMAC signing failed", e);
        }
    }
}
