package com.order.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 网关来源验签拦截器：校验 X-Gateway-Token 中的 HMAC 签名及时间戳时效。
 * Token 格式：timestamp.base64(HMAC-SHA256(timestamp, secretKey))
 */
@Component
public class GatewayOriginInterceptor implements HandlerInterceptor {

    /** 请求头名称，用于携带 HMAC 签名令牌 */
    private static final String TOKEN_HEADER = "X-Gateway-Token";

    /** HMAC 签名密钥，从配置文件读取 */
    @Value("${gateway.auth.secret-key}")
    private String secretKey;

    /** 令牌有效期（秒），超时则拒绝 */
    @Value("${gateway.auth.expire-seconds}")
    private long expireSeconds;

    /**
     * 请求前置拦截，校验 X-Gateway-Token 签名令牌。
     * <p>无令牌或验签失败则返回 403 Forbidden。</p>
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(TOKEN_HEADER);
        if (token == null || !verifyToken(token)) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }
        return true;
    }

    /**
     * 验证令牌合法性：解析时间戳和签名 → 校验时效 → 重算签名并比对。
     *
     * @param token 令牌字符串，格式为 "timestamp.signature"
     * @return 验证通过返回 true，否则返回 false
     */
    private boolean verifyToken(String token) {
        try {
            int dotIndex = token.indexOf('.');
            if (dotIndex <= 0 || dotIndex >= token.length() - 1) {
                return false;
            }
            String timestamp = token.substring(0, dotIndex);
            String signature = token.substring(dotIndex + 1);

            // 校验时效
            long ts = Long.parseLong(timestamp);
            if (Math.abs(System.currentTimeMillis() - ts) > expireSeconds * 1000) {
                return false;
            }

            // 验签
            String expected = sign(timestamp, secretKey);
            return expected.equals(signature);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 使用 HMAC-SHA256 算法对数据进行签名。
     *
     * @param data 待签名数据
     * @param key  签名密钥
     * @return Base64 编码的签名字符串
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
