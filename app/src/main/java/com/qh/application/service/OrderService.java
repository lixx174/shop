package com.qh.application.service;

import com.qh.application.model.command.OrderLaunchCommand;
import com.qh.domain.client.PaymentClient;
import com.qh.domain.client.WechatJsApiPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jinx
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final PaymentClient paymentClient;


    public String prepareLaunch() {
        return null;
    }

    public void launch(OrderLaunchCommand command) {

        paymentClient.pay();
        WechatJsApiPayment wechatJsApiPayment = paymentClient.wechatPay();

        System.out.println("pay successfully...");
        System.out.println(wechatJsApiPayment);
    }
}
