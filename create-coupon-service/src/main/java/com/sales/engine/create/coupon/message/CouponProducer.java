package com.sales.engine.create.coupon.message;

import com.sales.engine.create.coupon.dto.CouponDto;

public interface CouponProducer {
    void sendCreatedCouponMessage(CouponDto coupon);
}
