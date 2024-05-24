package com.qh.application.service;

import com.qh.application.model.Result;
import com.qh.application.model.command.OrderLaunchCommand;
import com.qh.infra.feign.PaymentCommand;
import com.qh.infra.feign.client.PaymentClient;
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

        //TODO feign 调用 结果处理


        Result<Void> result = paymentClient.pay(new PaymentCommand());

        System.out.println("result = " + result);
    }
}
