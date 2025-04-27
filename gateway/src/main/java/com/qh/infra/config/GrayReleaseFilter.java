package com.qh.infra.config;

import com.qh.GatewayConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

/**
 * FIXME 测试环境有效 通过ConditionalOn控制
 *
 * @author Jinx
 */
@Order(1)
@Configuration
@RequiredArgsConstructor
public class GrayReleaseFilter implements GlobalFilter {

    private final GatewayConfiguration configuration;
    /**
     * 灰度请求头 value为字符串：true|false
     */
    public static final String GRAY_LIMITING_HEADER = "X-Gateway-Gray";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        GatewayConfiguration.GrayRelease grayRelease = configuration.getGrayRelease();
        if (grayRelease.isEnable()) {
            ServerHttpRequest request = exchange.getRequest();
            InetSocketAddress remoteAddress = request.getRemoteAddress();
            assert remoteAddress != null;

            // FIXME 通过配置route的predicate进行分流，通过header判断 走那个服务
            boolean gray = grayRelease.getIps().contains(remoteAddress.getHostName());
            ServerHttpRequest newRequest = request.mutate()
                    .header(GRAY_LIMITING_HEADER, String.valueOf(gray))
                    .build();

            return chain.filter(exchange.mutate().request(newRequest).build());
        }

        return chain.filter(exchange);
    }
}
