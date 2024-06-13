package com.qh.application.service;

import com.qh.application.assembler.CouponAssembler;
import com.qh.application.model.CouponDto;
import com.qh.domain.UserCoupon;
import com.qh.domain.primitive.UserId;
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

    private final CouponAssembler assembler;

    public List<CouponDto> getUsableList() {
        // TODO get from request
        UserId userId = new UserId(0);

        // TODO bounded context
        // FIXME UserCoupon作为单独的领域 是否需要一个聚合根来聚合这些dp
        List<UserCoupon> userCoupons = repo.findByUserId(userId)
                .stream()
                .filter(UserCoupon::isUsable)
                .toList();

        return assembler.assemble(userCoupons);
    }
}
