package com.qh.application;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jinx
 */
@Getter
@Setter
public class PaymentCommand {

    /**
     * 支付方式
     */
    private Payment payment;
}
