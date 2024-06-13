package com.qh.infra.config;

import com.qh.infra.ServerWebExchangeSupport;
import com.qh.infra.token.TokenResolver;
import com.qh.infra.token.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 授权拦截器
 *
 * @author Jinx
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizationFilter implements GlobalFilter {


    private final TokenResolver tokenResolver;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        UserDetail userDetail = tokenResolver.resolve(exchange.getRequest());

        if (userDetail != null) {
            System.out.println("===> userDetail : " + userDetail);
            return chain.filter(exchange);
        }

        return ServerWebExchangeSupport.onCompletion(exchange, HttpStatus.UNAUTHORIZED);
    }


}
