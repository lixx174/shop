package com.qh.infra.config;

import com.qh.infra.exception.AbstractExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Jinx
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnMissingBean(AbstractExceptionHandler.class)
public class ExceptionHandlerAutoConfiguration {

    private final Environment environment;

    /**
     * 默认的全局异常处理器
     */
    @RestControllerAdvice
    public class DelegateExceptionHandler extends AbstractExceptionHandler {
        public DelegateExceptionHandler() {
            super(environment);
        }
    }
}
