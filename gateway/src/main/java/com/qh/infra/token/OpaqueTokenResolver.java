package com.qh.infra.token;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Jinx
 */
@Component
public class OpaqueTokenResolver implements TokenResolver {
    @Override
    public UserDetail resolve(ServerHttpRequest request) {
        String tokenValue = request.getHeaders().getFirst("authorization");

        if (StringUtils.hasText(tokenValue) && tokenValue.startsWith("Bearer ")) {
            tokenValue = tokenValue.substring(7);

            // TODO 校验token  解析token
            // FIXME 自己调用redis获取 / 远程调用认证服务获取


        }

        return null;
    }
}
