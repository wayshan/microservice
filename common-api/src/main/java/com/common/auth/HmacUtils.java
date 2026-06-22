package com.common.auth;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * HMAC-SHA256 签名工具类
 * <p>提供统一的签名生成和令牌验证能力，供网关和各微服务共用，消除重复代码。</p>
 * <p>令牌格式：{@code timestamp.base64(HMAC-SHA256(timestamp, secretKey))}</p>
 */
public final class HmacUtils {

    /** HMAC 算法名称 */
    private static final String ALGORITHM = "HmacSHA256";

    private HmacUtils() {
        // 工具类禁止实例化
    }

    /**
     * 使用 HMAC-SHA256 算法对数据进行签名。
     *
     * @param data 待签名的数据（通常为时间戳）
     * @param key  签名密钥
     * @return Base64 编码的签名字符串
     */
    public static String sign(String data, String key) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM));
            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("HMAC signing failed", e);
        }
    }

    /**
     * 根据时间戳生成完整的签名令牌。
     *
     * @param timestamp 时间戳字符串
     * @param key       签名密钥
     * @return 格式为 "timestamp.signature" 的令牌
     */
    public static String generateToken(String timestamp, String key) {
        return timestamp + "." + sign(timestamp, key);
    }

    /**
     * 验证 HMAC 签名令牌的合法性（签名正确 + 时间戳未过期）。
     *
     * @param token        令牌字符串，格式为 "timestamp.signature"
     * @param secretKey    签名密钥
     * @param expireSeconds 令牌有效期（秒），超过则拒绝
     * @return 验证通过返回 true，否则返回 false
     */
    public static boolean verifyToken(String token, String secretKey, long expireSeconds) {
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
}
