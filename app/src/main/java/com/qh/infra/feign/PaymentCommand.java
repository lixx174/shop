package com.qh.infra.feign;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jinx
 */
@Getter
@Setter
public class PaymentCommand {

    private String payment = "WECHAT";
}
