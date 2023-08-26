package com.sales.engine.create.coupon.service;

import com.sales.engine.create.coupon.dto.CouponDto;
import com.sales.engine.create.coupon.dto.CreateCouponRequest;

import java.util.List;
import java.util.UUID;

public interface CouponService {
    CouponDto getCouponById(UUID uuid);
    CouponDto createCoupon(CreateCouponRequest createCouponRequest);
    List<CouponDto> getAllCoupons();
}
