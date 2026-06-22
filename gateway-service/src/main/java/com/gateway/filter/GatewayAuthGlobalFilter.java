package com.gateway.filter;

import com.common.auth.HmacUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关全局过滤器：为每个转发请求生成 HMAC-SHA256 签名令牌。
 * Token 格式：X-Gateway-Token = timestamp.base64(HMAC-SHA256(timestamp, secretKey))
 */
@Component
public class GatewayAuthGlobalFilter implements GlobalFilter, Ordered {

    /** HMAC 签名密钥，从配置文件 gateway.auth.secret-key 读取 */
    @Value("${gateway.auth.secret-key}")
    private String secretKey;

    /**
     * 拦截每个请求，生成 HMAC-SHA256 签名令牌并注入请求头。
     * <p>处理流程：</p>
     * <ol>
     *   <li>获取当前时间戳</li>
     *   <li>使用密钥对时间戳进行 HMAC-SHA256 签名</li>
     *   <li>拼接为 "timestamp.signature" 格式</li>
     *   <li>将令牌放入 X-Gateway-Token 请求头，随请求转发到下游微服务</li>
     * </ol>
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String token = HmacUtils.generateToken(timestamp, secretKey);

        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                .header("X-Gateway-Token", token)
                .build();

        return chain.filter(exchange.mutate().request(mutatedRequest).build());
    }

    /**
     * 过滤器执行顺序，值越小越先执行。
     * 设置为 -200 确保签名令牌尽早注入，供后续过滤器和路由使用。
     */
    @Override
    public int getOrder() {
        return -200;
    }
}
