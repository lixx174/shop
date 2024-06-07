package com.qh.infra.repository;

import com.qh.domain.UserCoupon;
import com.qh.domain.repository.CouponRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jinx
 */
@Repository
public class DefaultCouponRepository implements CouponRepository {
    @Override
    public List<UserCoupon> findByUserId(Integer userId) {
        return null;
    }
}
