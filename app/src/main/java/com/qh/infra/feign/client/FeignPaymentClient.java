package com.qh.infra.feign.client;

import com.qh.application.model.Result;
import com.qh.domain.client.PaymentClient;
import com.qh.domain.client.WechatJsApiPayment;
import com.qh.infra.ResultHandler;
import com.qh.infra.feign.FeignClientFallbackFactory;
import com.qh.infra.feign.PaymentCommand;
import com.qh.infra.feign.WechatJsApiPaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Jinx
 */
@Component
@RequiredArgsConstructor
public class FeignPaymentClient implements PaymentClient {

    private final PaymentFeignClient delegateClient;
    private final ResultHandler handler;

    @Override
    public void pay() {
        Result<Void> result = delegateClient.pay(new PaymentCommand());

        handler.handle(result);
    }

    @Override
    public WechatJsApiPayment wechatPay() {
        Result<WechatJsApiPaymentDto> result = delegateClient.wechatPay(new PaymentCommand());

        // TODO 支付领域？
        WechatJsApiPaymentDto dto = handler.handle(result);

        // dto -> domain
        return new WechatJsApiPayment();
    }


    @FeignClient(value = "shop-payment", fallbackFactory = FeignClientFallbackFactory.class)
    public interface PaymentFeignClient {

        @PostMapping("/payment/plat")
        Result<Void> pay(@RequestBody PaymentCommand command);

        @PostMapping("/payment/wechat")
        Result<WechatJsApiPaymentDto> wechatPay(@RequestBody PaymentCommand command);
    }
}
