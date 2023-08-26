package com.sales.engine.create.coupon.controller;

import com.sales.engine.create.coupon.dto.CouponDto;
import com.sales.engine.create.coupon.dto.CreateCouponRequest;
import com.sales.engine.create.coupon.service.CouponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/coupon")
public class CreateCouponController {

    private final CouponService couponService;

    public CreateCouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/{uuid}")
    CouponDto getCoupon(@PathVariable String uuid) {
        return couponService.getCouponById(UUID.fromString(uuid));
    }

    @GetMapping
    List<CouponDto> getCoupon() {
        return couponService.getAllCoupons();
    }

    @PostMapping
    CouponDto createCoupon(@RequestBody CreateCouponRequest createCouponRequest){
        return couponService.createCoupon(createCouponRequest);
    }
}
