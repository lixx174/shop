package com.qh.infra;

import com.qh.application.model.Result;
import com.qh.domain.RestException;
import org.springframework.stereotype.Component;

/**
 * {@link Result} 处理器
 *
 * @author Jinx
 */
public interface ResultHandler {

    <T> T handle(Result<T> result);


    @Component
    class DefaultResultHandler implements ResultHandler {
        @Override
        public <T> T handle(Result<T> result) {
            if (result == null) {
                throw new RestException("empty response");
            }

            if (!result.success()) {
                throw new RestException("response failed : %s-%s".formatted(result.getCode(), result.getMsg()));
            }

            return result.getData();
        }
    }
}
