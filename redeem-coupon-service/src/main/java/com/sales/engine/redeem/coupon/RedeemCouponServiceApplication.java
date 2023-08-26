package com.sales.engine.redeem.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication(scanBasePackages = "com.sales.engine.redeem.coupon")
@EnableMongoAuditing
public class RedeemCouponServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedeemCouponServiceApplication.class, args);
    }
}

