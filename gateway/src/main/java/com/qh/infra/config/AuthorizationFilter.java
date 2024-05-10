package com.qh.infra.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 授权拦截器
 *
 * @author Jinx
 */
@Configuration
public class AuthorizationFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String tokenValue = exchange.getRequest().getHeaders().getFirst("authorization");
        if(StringUtils.hasText(tokenValue) && tokenValue.startsWith("Bearer ")){
            tokenValue = tokenValue.substring(7);

            // TODO 校验token  解析token
            // FIXME 自己调用redis获取 / 远程调用认证服务获取

            System.out.println("===> tokenValue : " + tokenValue);
            return chain.filter(exchange);
        }


        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}
