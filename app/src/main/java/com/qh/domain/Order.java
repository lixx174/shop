package com.qh.domain;

import com.qh.domain.primitive.OrderId;
import com.qh.domain.primitive.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单
 *
 * @author Jinx
 */
public class Order {

    /**
     *  订单id
     */
    private OrderId id;

    /**
     *  订单金额
     */
    private BigDecimal amount;

    private List<Coupon> coupons;

    /**
     *  订单状态
     */
    private OrderStatus status;



}
