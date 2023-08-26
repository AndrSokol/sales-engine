package com.sales.engine.redeem.coupon.service.impl;

import com.sales.engine.redeem.coupon.collection.Coupon;
import com.sales.engine.redeem.coupon.repository.CouponRepository;
import com.sales.engine.redeem.coupon.service.CouponService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    @Cacheable(value = "couponCreatedKafkaKeys", key = "{#coupon.couponUuid}")
    public Coupon save(Coupon coupon) {
        return couponRepository.save(coupon);
    }
}
