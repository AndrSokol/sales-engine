package com.sales.engine.create.coupon.repository;

import com.sales.engine.create.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, UUID> {
    Optional<Coupon> findCouponByCompanyUuid(UUID companyUuid);
}
