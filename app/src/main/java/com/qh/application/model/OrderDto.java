package com.qh.application.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单
 *
 * @author Jinx
 */
@Getter
@Setter
public class OrderDto {

    /**
     * 订单id
     */
    private Integer id;
    /**
     * 订单号
     */
    private String number;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 订单金额
     */
    private BigDecimal amount;
    /**
     * 订单状态
     */
    private String status;
    /**
     * 支付状态
     */
    private String paymentStatus;
    /**
     * 支付方式
     */
    private String paymentMethod;
    /**
     * 创建时间
     */
    private LocalDateTime createAt;
}
