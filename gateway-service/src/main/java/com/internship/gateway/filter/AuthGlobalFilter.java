package com.internship.gateway.filter;

import com.internship.common.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {


    @Autowired
    JwtUtils jwtUtils;

    // 1. 白名单配置：这些接口不需要保安查 Token 就能进（比如登录、注册）
    private static final List<String> WHITE_LIST = Arrays.asList(
            "/auth/login",
            "/auth/register"
            // 注意：/api/user/info/** 是内部 Feign 调用的，网关这里一般不用对外暴露
    );

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // 2. 白名单放行逻辑
        for (String whitePath : WHITE_LIST) {
            if (pathMatcher.match(whitePath, path)) {
                return chain.filter(exchange); // 属于白名单，直接放行
            }
        }

        // 3. 核心安保：从请求头（Header）中提取 Token
        // 行业规范：前端传 Token 时，通常放在名为 "Authorization" 的请求头里
        String token = request.getHeaders().getFirst("Authorization");

        if (!StringUtils.hasText(token)) {
            return reject(exchange, "未授权：请求头缺失 Token！");
        }

        try {
            // 4. 验证 Token（这里你需要调用你自己的 JwtUtil 去解析它）
            // 假设你解析成功，拿到了用户的真实 ID：
             Long userId = jwtUtils.getUserIdFromToken(token);

            // 👑 5. 架构师的绝技：请求头透传（Request Mutation）！
            // 网关解析出 userId 后，直接把它塞进请求头里，传给后面的微服务。
            // 这样后面的订单服务、商品服务就不用再解析 Token 了，直接通过 Header 就能知道是谁发起的请求！
            ServerHttpRequest mutatedRequest = request.mutate()
                    .header("X-User-Id", String.valueOf(userId))
                    .build();

            // 带着重新包装好的请求，继续往后走
            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        } catch (Exception e) {
            // Token 过期、被篡改，都会走到这里
            return reject(exchange, "Token 已过期或不合法，请重新登录！");
        }

    }

    /**
     * 拦截并返回 401 Unauthorized 错误（纯 WebFlux 响应式写法）
     */
    private Mono<Void> reject(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();

        // 设置 HTTP 状态码为 401
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        // 告诉前端返回的是 JSON
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        // 包装咱们大厂标准格式的 Result JSON
        String jsonResult = String.format("{\"code\": 401, \"message\": \"%s\", \"data\": null}", message);
        DataBuffer dataBuffer = response.bufferFactory().wrap(jsonResult.getBytes(StandardCharsets.UTF_8));

        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        // 决定这个过滤器的优先级，数字越小，越先执行（在网关内部的最外层）
        return -100;
    }
}