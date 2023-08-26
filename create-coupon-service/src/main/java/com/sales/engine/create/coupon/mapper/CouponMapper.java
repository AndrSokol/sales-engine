package com.sales.engine.create.coupon.mapper;

import com.sales.engine.create.coupon.dto.CompanyResponse;
import com.sales.engine.create.coupon.dto.CouponDto;
import com.sales.engine.create.coupon.entity.Coupon;

import java.util.List;

public interface CouponMapper{
    CouponDto couponToDto(Coupon coupon);
    List<CouponDto> entityListToDtoList(List<Coupon> entityList);

    CouponDto couponToDto(Coupon coupon, CompanyResponse companyResponse);
}
