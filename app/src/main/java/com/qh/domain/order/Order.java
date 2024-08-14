package com.qh.domain.order;

import com.qh.domain.OrderNumber;
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
 * <p>
 * TODO 通过构造函数初始化  领域状态不需要通过set来改变  如果是这样构造函数是不是过于复杂？
 * <p>
 * TODO 去掉 do层 直接把domain作为do
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
     * 支付状态
     */
    private Payment.Status paymentStatus;
    /**
     * 支付方式
     */
    private Payment.Method paymentMethod;
    /**
     * 创建时间
     */
    private LocalDateTime createAt;
}
