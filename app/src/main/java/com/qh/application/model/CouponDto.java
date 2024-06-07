package com.qh.application.model;

import com.qh.domain.primitive.Coupons;
import com.qh.domain.primitive.Period;

import java.math.BigDecimal;

/**
 * 优惠卷
 *
 * @author Jinx
 */
public class CouponDto {

    private String id;
    /**
     * 优惠卷名称
     */
    private String name;
    /**
     * 优惠卷类型
     */
    private Coupons type;
    /**
     * 折扣百分比 - {@link Coupons#DISCOUNT}时会有值
     */
    private BigDecimal discount;
    /**
     * 优惠金额 - {@link Coupons#FULL_DISCOUNT}或{@link Coupons#CASH}时会有值
     */
    private BigDecimal amount;
    /**
     * 最低金额 - {@link Coupons#FULL_DISCOUNT}时会有值
     */
    private BigDecimal minimumAmount;
    /**
     * 优惠券有效时间段 - {@link Coupons#LIMITED_TIME}时会有值
     */
    private Period period;
    /**
     * 备注 - 使用说明
     */
    private String remark;
}
