package com.sales.engine.create.coupon.dto;

import java.util.UUID;

public record CreateCouponRequest(String promoCode,
                                  UUID companyUuid) {
}
