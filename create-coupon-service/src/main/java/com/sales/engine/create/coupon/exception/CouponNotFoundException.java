package com.sales.engine.create.coupon.exception;

public class CouponNotFoundException extends RuntimeException{
    public CouponNotFoundException(String message) {
        super(message);
    }
}
