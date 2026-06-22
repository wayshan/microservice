package com.common.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 网关来源验签拦截器：校验 X-Gateway-Token 中的 HMAC 签名及时间戳时效。
 * <p>放在 common-api 中供所有下游微服务复用，避免各服务重复编写验签逻辑。</p>
 * <p>Token 格式：timestamp.base64(HMAC-SHA256(timestamp, secretKey))</p>
 */
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
        if (token == null || !HmacUtils.verifyToken(token, secretKey, expireSeconds)) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }
        return true;
    }
}
