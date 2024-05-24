package com.qh.infra.feign;

import com.qh.application.model.Result;
import com.qh.domain.RestException;
import com.qh.infra.feign.client.PaymentClient;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.Targeter;
import org.springframework.stereotype.Component;

/**
 * 默认环境下 openfeign不支持Fallback  需要引入 Spring Cloud Circuit Breaker 相关依赖（ Hystrix 的替代）
 * FIXME 也可以使用 sentinel 实现服务降级
 *
 * @author Jinx
 * @see CircuitBreakerFactory 默认情况下容器内无该bean，导入服务治理相关依赖后会注入该 factory
 * @see Targeter 当容器内存在 {@link CircuitBreakerFactory} 时，会注入 FeignCircuitBreakerTargeter 默认是 DefaultTargeter
 */
@Component
public class FeignClientFallbackFactory implements FallbackFactory<PaymentClient> {

    /**
     * 服务降级 - 快速失败
     */
    @Override
    public PaymentClient create(Throwable cause) {

        // 只会出现 服务不可用 / 404 等异常
        // 只要请求打到了服务 就一定是 Result 类型的响应

        // TODO 服务降级
        return new PaymentClient() {
            @Override
            public Result<Void> pay(PaymentCommand command) {
                throw new RestException("%s 服务繁忙".formatted("shop-payment"));
            }
        };
    }
}
