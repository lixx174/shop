package com.qh.application.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠卷
 *
 * @author Jinx
 */
@Getter
@Setter
public class CouponDto {

    private String id;
    /**
     * 优惠卷名称
     */
    private String name;
    /**
     * 优惠卷类型
     */
    private String type;
    /**
     * 折扣百分比
     */
    private BigDecimal discount;
    /**
     * 优惠金额
     */
    private BigDecimal amount;
    /**
     * 最低金额
     */
    private BigDecimal minimumAmount;
    /**
     * 优惠券有效时间开始时间
     */
    private LocalDateTime startTime;
    /**
     * 优惠券有效时间结束时间
     */
    private LocalDateTime endTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 优惠卷数量
     */
    private Integer count;
}
