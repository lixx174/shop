package com.qh.application.service;

import com.qh.application.PaymentCommand;
import com.qh.application.WechatJsApiPaymentDto;
import org.springframework.stereotype.Service;

/**
 * @author Jinx
 */
@Service
public class PaymentService {

    public WechatJsApiPaymentDto pay(PaymentCommand command) {

        System.out.println("command = " + command);

        return new WechatJsApiPaymentDto();
    }
}
