package com.qh.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Jinx
 */
public class ServerWebExchangeSupport {


    /**
     * 结束请求
     *
     * @param exchange   当前请求
     * @param httpStatus 状态码
     * @return 结束
     */
    public static Mono<Void> onCompletion(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
