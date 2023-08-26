package com.sales.engine.redeem.coupon.repository;

import com.sales.engine.redeem.coupon.collection.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CouponRepository extends MongoRepository<Coupon, UUID> {
}
