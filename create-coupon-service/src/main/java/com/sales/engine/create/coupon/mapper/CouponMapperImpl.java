package com.sales.engine.create.coupon.mapper;

import com.sales.engine.create.coupon.dto.CompanyResponse;
import com.sales.engine.create.coupon.dto.CouponDto;
import com.sales.engine.create.coupon.entity.Coupon;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponMapperImpl implements CouponMapper {
    @Override
    public List<CouponDto> entityListToDtoList(List<Coupon> entityList) {
        return entityList.stream().map(this::couponToDto).toList();
    }

    @Override
    public CouponDto couponToDto(Coupon coupon, CompanyResponse companyResponse) {
        return CouponDto.builder()
                .uuid(coupon.getUuid())
                .promoCode(coupon.getPromoCode())
                .companyUuid(coupon.getCompanyUuid())
                .createdAt(coupon.getCreatedAt())
                .companyInfo(companyResponse)
                .build();
    }

    @Override
    public CouponDto couponToDto(Coupon coupon) {
        return CouponDto.builder()
                .uuid(coupon.getUuid())
                .promoCode(coupon.getPromoCode())
                .companyUuid(coupon.getCompanyUuid())
                .createdAt(coupon.getCreatedAt())
                .build();
    }
}
