package com.qh.infra.config;

import com.qh.domain.RedisClient;
import com.qh.domain.UserDetail;
import com.qh.infra.ServerWebExchangeSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * 授权拦截器
 *
 * @author Jinx
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizationFilter implements GlobalFilter {

    private final RedisClient redisClient;
    private final PathMatcher matcher = new AntPathMatcher();
    private final WhiteList wl = new WhiteList();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 白名单
        if (wl.match(exchange.getRequest().getPath())) {
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst("authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            UserDetail userDetail = redisClient.get(() -> token.substring(7), UserDetail.class);

            if (userDetail != null) {
                return chain.filter(exchange);
            }
        }

        return ServerWebExchangeSupport.onCompletion(exchange, HttpStatus.UNAUTHORIZED);
    }

    private class WhiteList {
        private final Set<String> values = Set.of("oauth2/**");

        public boolean match(RequestPath path) {
            return values.stream().noneMatch(str -> matcher.match(str, path.value()));
        }
    }
}
