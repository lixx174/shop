package com.qh.infra.feign;

import com.qh.infra.config.FeignConfig;
import feign.Client;
import feign.Request;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * feign client 代理 - 代理当前context注入的client
 * <p>
 * 用于实现 交互日志记录，该处可记录最原始的交互日志
 *
 * @author Jinx
 * @see FeignConfig
 */
@RequiredArgsConstructor
public class FeignClientProxy implements Client {

    private final Client target;

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        String clientName = request.requestTemplate().feignTarget().name();

        System.out.printf(
                "feign(%s) request ===> [%s - %s] -> %s%n",
                clientName,
                request.url(),
                request.httpMethod(),
                request.body() == null ? "" : new String(request.body())
        );

        Response response;
        try {
            response = target.execute(request, options);
        } catch (Exception e) {
            System.out.printf("feign(%s) response ===> occur an exception -> %s%n", clientName, e.getCause());
            throw e;
        }

        if (response == null) {
            System.out.printf("feign(%s) response ===> %s%n", clientName, "empty response");
        } else {
            // TODO response 的 inputStream 只能读取一次
            System.out.printf(
                    "feign(%s) response ===> [%s - %s] -> %s%n",
                    clientName,
                    response.status(),
                    response.reason(),
                    StreamUtils.copyToString(response.body().asInputStream(), StandardCharsets.UTF_8)
            );
        }

        return response;
    }
}
