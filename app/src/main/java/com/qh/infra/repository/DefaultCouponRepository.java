package com.qh.infra.repository;

import com.qh.domain.Coupon;
import com.qh.domain.UserCoupon;
import com.qh.domain.primitive.CouponId;
import com.qh.domain.primitive.UserId;
import com.qh.domain.repository.CouponRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jinx
 */
@Repository
public class DefaultCouponRepository implements CouponRepository {
    @Override
    public Coupon findById(CouponId id) {
        return null;
    }

    @Override
    public List<UserCoupon> findByUserId(UserId userId) {
        return null;
    }
}
