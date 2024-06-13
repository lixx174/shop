package com.qh.domain;

import com.qh.domain.primitive.CouponQuantity;
import com.qh.domain.primitive.Coupons;
import com.qh.domain.primitive.UserCouponId;
import com.qh.domain.primitive.UserId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 用户优惠卷
 * <p>
 * FIXME 不能算作单纯的多对多 因为该关联还会有其它属性
 *
 * @author Jinx
 */
@Getter
@Setter
public class UserCoupon {

    private UserCouponId id;
    /**
     * 用户
     */
    private UserId userId;
    /**
     * 优惠卷
     * FIXME 该处使用id？ 但是如若是id 如何进行 assemble
     */
    private Coupon coupon;
    /**
     * 优惠卷数量
     */
    private CouponQuantity couponQuantity;


    /**
     * 用户优惠卷是否是可用
     * <p>
     * TODO 适用店铺  跨店等
     *
     * @return true:可用
     */
    public boolean isUsable() {
        if (!coupon.getActive()) {
            return false;
        }

        if (coupon.getType() == Coupons.LIMITED_TIME) {
            return coupon.getPeriod().isBetween(LocalDateTime.now());
        }

        return true;
    }
}
