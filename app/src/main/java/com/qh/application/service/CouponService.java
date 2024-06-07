package com.qh.application.service;

import com.qh.application.model.CouponDto;
import com.qh.domain.UserCoupon;
import com.qh.domain.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jinx
 */
@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository repo;


    public List<CouponDto> getUsableList() {
        // TODO get from request
        Integer userId = 0;

        // TODO 领域边界  界限上下文
        List<UserCoupon> userCoupons = repo.findByUserId(userId);

        return null;
    }
}
