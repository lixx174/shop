package com.qh.infra.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

/**
 * @author Jinx
 */
@Configuration
public class RateLimiterConfig {

    /**
     * 限流 唯一键
     * 通过 route.json 中 指定route的filter配置实现（RequestRateLimiter）
     *
     * @return ip + path
     */
    @Bean
    public KeyResolver ipPathKeyResolver() {
        return exchange -> {
            ServerHttpRequest request = exchange.getRequest();
            InetSocketAddress remoteAddress = request.getRemoteAddress();

            return remoteAddress == null ?
                    Mono.empty() :
                    Mono.just(remoteAddress.getHostName() + ":" + request.getPath());
        };
    }
}
