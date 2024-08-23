package com.qh.infra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qh.application.model.UserInfo;
import com.qh.domain.RedisClient;
import com.qh.domain.UserDetail;
import com.qh.infra.ServerWebExchangeSupport;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Base64;
import java.util.Set;

/**
 * 授权拦截器
 *
 * @author Jinx
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizationFilter implements GlobalFilter {

    private final ObjectMapper om = new ObjectMapper();
    private final RedisClient redisClient;
    private final PathMatcher matcher = new AntPathMatcher();
    private final WhiteList wl = new WhiteList();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        // 白名单
        if (wl.match(path)) {
            return chain.filter(exchange);
        }


        String token = exchange.getRequest().getHeaders().getFirst("authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            UserDetail userDetail = redisClient.get(() -> token.substring(7), UserDetail.class);

            if (userDetail != null && userDetail.isAuthorized(p -> matcher.match(p, path))) {
                return chain.filter(
                        exchange
                                .mutate()
                                .request(
                                        exchange.getRequest().mutate()
                                                .header(
                                                        "userinfo",
                                                        encrypt(new UserInfo(userDetail.getId()))
                                                )
                                                .build()
                                )
                                .build()
                );
            }
        }

        return ServerWebExchangeSupport.onCompletion(exchange, HttpStatus.UNAUTHORIZED);
    }

    @SneakyThrows
    private String encrypt(UserInfo userInfo) {
        return Arrays.toString(Base64.getEncoder().encode(om.writeValueAsBytes(userInfo)));
    }

    private class WhiteList {
        private final Set<String> values = Set.of(
                "/auth/oauth2/**",
                "/message/sm/**",
                // FIXME  for test
                "/admin/**"
        );

        public boolean match(String path) {
            return values.stream().anyMatch(str -> matcher.match(str, path));
        }
    }
}
