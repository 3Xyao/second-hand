package com.internship.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
@RefreshScope
public class JwtUtils {

    // 1. 公钥：所有微服务共用（放在 Nacos 共享配置中）
    @Value("${jwt.public-key:}")
    private String publicKeyStr;

    // 2. 私钥：只有 auth-service 有权持有（放在 auth-service 专用配置中）
    // 加上 :# {null} 是为了让其他服务找不到配置时不报错
    @Value("${jwt.private-key:#{null}}")
    private String privateKeyStr;

    @Value("${jwt.expiration:3600000}")
    private long expiration;

    // --- 🔑 密钥转换核心方法 ---

    /**
     * 将 Base64 字符串转换为 RSA 公钥对象 (用于验签)
     */
    private PublicKey getPublicKey() throws Exception {
        byte[] keyBytes = Base64.getMimeDecoder().decode(publicKeyStr.getBytes(StandardCharsets.UTF_8));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    /**
     * 将 Base64 字符串转换为 RSA 私钥对象 (仅用于签发)
     */
    private PrivateKey getPrivateKey() throws Exception {
        if (!StringUtils.hasText(privateKeyStr)) {
            throw new RuntimeException("当前服务未配置私钥，无法签发 Token！");
        }
        byte[] keyBytes = Base64.getMimeDecoder().decode(privateKeyStr.getBytes(StandardCharsets.UTF_8));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    // --- 👑 核心业务方法 ---

    /**
     * 签发 Token (仅 Auth 服务使用私钥签名)
     */
    public String generateToken(Long userId) {
        try {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + expiration);

            return Jwts.builder()
                    .header().type("JWT").and()
                    .claim("userId", userId)
                    .issuedAt(now)
                    .expiration(expiryDate)
                    // 关键：使用私钥签名，算法指定为 RS256
                    .signWith(getPrivateKey(), Jwts.SIG.RS256)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("生成 Token 失败: " + e.getMessage());
        }
    }

    /**
     * 解析并校验 Token (所有服务使用公钥验签)
     */
    public Claims parseToken(String token) {
        try {
            // 处理 Bearer 前缀
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            return Jwts.parser()
                    // 关键：使用公钥进行验证
                    .verifyWith(getPublicKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new RuntimeException("Token 校验失败或已过期");
        }
    }

    /**
     * 直接从 Token 中提取用户 ID
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = parseToken(token);
        return Long.valueOf(claims.get("userId").toString());
    }
}