package com.qh.domain.repository;

import com.qh.domain.Coupon;
import com.qh.domain.UserCoupon;
import com.qh.domain.primitive.CouponId;
import com.qh.domain.primitive.UserId;

import java.util.List;

/**
 * @author Jinx
 */
public interface CouponRepository {

    Coupon findById(CouponId id);

    /**
     * 通过userId 获取 用户优惠卷
     *
     * @param userId 用户id
     * @return 用户优惠卷集合
     */
    List<UserCoupon> findByUserId(UserId userId);
}
