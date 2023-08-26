package com.sales.engine.create.coupon.repository.impl;

import com.sales.engine.create.coupon.entity.Coupon;
import com.sales.engine.create.coupon.repository.CouponCustomRepository;
import com.sales.engine.create.coupon.repository.CouponRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CouponCustomRepositoryImpl implements CouponCustomRepository {

    private final CouponRepository couponRepository;

    public CouponCustomRepositoryImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public Optional<Coupon> findById(UUID uuid) {
        return couponRepository.findById(uuid);
    }

    @Override
    public Optional<Coupon> findByCompanyUuid(UUID companyUuid) {
        return couponRepository.findCouponByCompanyUuid(companyUuid);
    }

    @Override
    public Coupon save(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }
}
