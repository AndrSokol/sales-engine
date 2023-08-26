package com.sales.engine.create.coupon.repository;

import com.sales.engine.create.coupon.entity.Coupon;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CouponCustomRepository {
    Optional<Coupon> findById(UUID uuid);
    Optional<Coupon> findByCompanyUuid(UUID companyName);
    Coupon save(Coupon coupon);
    List<Coupon> findAll();
}
