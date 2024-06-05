package com.qh.domain.client;

/**
 * 支付client
 *
 * @author Jinx
 */
public interface PaymentClient {

    void pay();

    WechatJsApiPayment wechatPay();
}
