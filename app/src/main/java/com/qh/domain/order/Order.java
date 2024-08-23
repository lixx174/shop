package com.qh.domain.order;

import com.qh.domain.primitive.OrderId;
import com.qh.domain.primitive.OrderStatus;
import com.qh.domain.primitive.Payment;
import com.qh.domain.primitive.UserId;
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
public class Order {

    /**
     * 订单id
     */
    private OrderId id;
    /**
     * 订单号
     */
    private OrderNumber number;
    /**
     * 用户id
     */
    private UserId userId;
    /**
     * 订单金额
     * TODO 优惠卷
     */
    private BigDecimal amount;
    /**
     * 订单状态
     */
    private OrderStatus status;
    /**
     * 支付信息
     */
    private Payment payment;
    /**
     * 创建时间
     */
    private LocalDateTime createAt;
}
