package com.qh.domain;

import com.qh.domain.primitive.CouponId;
import com.qh.domain.primitive.CouponQuantity;
import com.qh.domain.primitive.UserCouponId;
import com.qh.domain.primitive.UserId;

/**
 * 用户优惠卷
 *
 * @author Jinx
 */
public class UserCoupon {

    private UserCouponId id;
    /**
     * 用户
     */
    private UserId userId;
    /**
     * 优惠卷
     */
    private CouponId couponId;
    /**
     * 优惠卷数量
     */
    private CouponQuantity couponQuantity;
}
