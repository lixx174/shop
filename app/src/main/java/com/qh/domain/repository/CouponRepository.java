package com.qh.domain.repository;

import com.qh.domain.UserCoupon;

import java.util.List;

/**
 * @author Jinx
 */
public interface CouponRepository {

    List<UserCoupon> findByUserId(Integer userId);
}
