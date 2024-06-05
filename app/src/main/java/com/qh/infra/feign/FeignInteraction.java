package com.qh.infra.feign;

import feign.Client;
import feign.InvocationContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.ResponseInterceptor;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;

/**
 * feign 交互日志
 * <p>
 * 低版本的 <a href="https://spring.io/security/cve-2024-22243">UriComponentsBuilder</a> 有漏洞 会导致idea警告
 *
 * @author Jinx
 * @deprecated 记录的响应日志不纯粹且无法记录到超时日志  参考{@link FeignClientProxy}
 */
@Deprecated
public class FeignInteraction implements RequestInterceptor, ResponseInterceptor {


    @Override
    public void apply(RequestTemplate template) {
        System.out.printf(
                "feign(%s) request ===> [%s - %s] -> %s%n",
                template.feignTarget().name(),
                UriComponentsBuilder.fromHttpUrl(template.feignTarget().url()).path(template.url()).toUriString(),
                template.method(),
                template.body() == null ? "" : new String(template.body())
        );
    }


    /**
     * TODO 只有成功响应才会调用到该回调  如果超时不会进入该拦截 会直接抛异常
     * 可以通过直接实现 {@link Client} 在调用前后打印日志
     */
    @Override
    public Object intercept(InvocationContext invocationContext, Chain chain) throws Exception {
        System.out.printf(
                "feign(%s) response ===> [%s - %s] -> %s%n",
                invocationContext.response().request().requestTemplate().feignTarget().name(),
                invocationContext.response().status(),
                invocationContext.response().reason(),
                StreamUtils.copyToString(invocationContext.response().body().asInputStream(), StandardCharsets.UTF_8)
        );

        return chain.next(invocationContext);
    }
}
