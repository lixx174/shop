package com.qh.infra.config;

import com.qh.application.model.Result;
import com.qh.domain.RestException;
import com.qh.infra.feign.FeignClientProxy;
import feign.Client;
import feign.InvocationContext;
import feign.ResponseInterceptor;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jinx
 */
@Configuration
public class FeignConfig implements BeanPostProcessor, ResponseInterceptor {

    /**
     * 替换 open feign 自动注入的{@link Client} 为 {@link FeignClientProxy}
     * <p>
     * 直接注入{@link FeignClientProxy}由于依赖{@link Client}会出现循环依赖
     * 可能由于client是外部依赖注入的原因（待商榷）
     */
    @Override
    public Object postProcessBeforeInitialization(@Nonnull Object bean, @Nullable String beanName) {
        return bean instanceof Client client ? new FeignClientProxy(client) : bean;
    }

    /**
     * feign client 后置处理， 用于校验响应是否成功
     * <p>
     * 所有的 feign client 都应该返回统一模型
     *
     * @see com.qh.application.model.Result
     */
    @Override
    public Object intercept(InvocationContext invocationContext, Chain chain) throws Exception {
        Object obj = chain.next(invocationContext);

        if (obj == null) {
            throw new RestException("empty response");
        }

        if (obj instanceof Result<?> result) {
            if (!result.success()) {
                throw new RestException("response failed : %s-%s".formatted(result.getCode(), result.getMsg()));
            }
        } else {
            // FIXME 服务启动时做检查？
            throw new RestException("feign client need use %s as response model".formatted(Result.class.getName()));
        }

        return obj;
    }
}
