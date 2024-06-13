package com.qh.infra.token;

import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * token解析器
 *
 * @author Jinx
 */
public interface TokenResolver {

    /**
     * 解析 token 字符串
     * <p>
     * 可能是无状态token 也可能是jwt
     *
     * @param request 请求信息
     * @return 用户信息
     */
    UserDetail resolve(ServerHttpRequest request);
}
