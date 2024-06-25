package com.qh.api;

import com.qh.application.model.CouponDto;
import com.qh.application.model.Result;
import com.qh.application.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 优惠卷
 *
 * @author Jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("coupon")
public class CouponController {

    private final CouponService service;

    /**
     * 可用优惠卷
     *
     * @return 可用优惠卷集合
     */
    @GetMapping("usable_list")
    public Result<List<CouponDto>> getUsableList() {
        return Result.ok(service.getUsableList());
    }
}
