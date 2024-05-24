package com.qh.infra.feign.client;

import com.qh.application.model.Result;
import com.qh.infra.feign.FeignClientFallbackFactory;
import com.qh.infra.feign.PaymentCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Jinx
 */
@FeignClient(value = "shop-payment", fallbackFactory = FeignClientFallbackFactory.class)
public interface PaymentClient {

    @PostMapping("/payment/plat")
    Result<Void> pay(@RequestBody PaymentCommand command);
}
