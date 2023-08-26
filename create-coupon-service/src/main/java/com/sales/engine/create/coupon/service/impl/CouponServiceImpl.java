package com.sales.engine.create.coupon.service.impl;

import com.sales.engine.create.coupon.dto.CompanyResponse;
import com.sales.engine.create.coupon.dto.CouponDto;
import com.sales.engine.create.coupon.dto.CreateCouponRequest;
import com.sales.engine.create.coupon.entity.Coupon;
import com.sales.engine.create.coupon.exception.CouponNotFoundException;
import com.sales.engine.create.coupon.mapper.CouponMapper;
import com.sales.engine.create.coupon.message.CouponProducer;
import com.sales.engine.create.coupon.repository.CouponCustomRepository;
import com.sales.engine.create.coupon.service.CompanyService;
import com.sales.engine.create.coupon.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CouponServiceImpl implements CouponService {

    private final CouponCustomRepository couponCustomRepository;
    private final CouponProducer couponProducer;
    private final CouponMapper couponMapper;
    private final CompanyService companyService;

    public CouponServiceImpl(CouponCustomRepository couponCustomRepository,
                             CouponProducer couponProducer,
                             CouponMapper couponMapper,
                             CompanyService companyService) {
        this.couponCustomRepository = couponCustomRepository;
        this.couponProducer = couponProducer;
        this.couponMapper = couponMapper;
        this.companyService = companyService;
    }

    @Override
    public CouponDto getCouponById(UUID uuid) {
        Optional<Coupon> couponOptional = couponCustomRepository.findById(uuid);
        Coupon coupon = couponOptional
                .orElseThrow(() -> new CouponNotFoundException(String.format("Coupon with uuid %s was now found", uuid)));

        CompanyResponse company = companyService.findCompanyByUuid(coupon.getCompanyUuid());

        if (company == null) {
            log.error("Company service unavailable. Coupon response is generating without company info");
            return couponMapper.couponToDto(coupon);
        }

        return couponMapper.couponToDto(coupon, company);
    }

    @Override
    @Transactional
    public CouponDto createCoupon(CreateCouponRequest createCouponRequest) {
        Coupon coupon = new Coupon();
        coupon.setUuid(UUID.randomUUID());
        coupon.setPromoCode(createCouponRequest.promoCode());
        coupon.setCompanyUuid(createCouponRequest.companyUuid());

        Coupon savedCoupon = couponCustomRepository.save(coupon);

        CompanyResponse companyByUuid = companyService.findCompanyByUuid(savedCoupon.getCompanyUuid());

        if (companyByUuid == null) {
            log.error("Company service unavailable. Coupon response is generating without company info");
            couponProducer.sendCreatedCouponMessage(couponMapper.couponToDto(savedCoupon));
        } else {
            couponProducer.sendCreatedCouponMessage(couponMapper.couponToDto(savedCoupon, companyByUuid));
        }

        return couponMapper.couponToDto(savedCoupon);
    }

    @Override
    @Cacheable(value = "couponsCache")
    public List<CouponDto> getAllCoupons() {
        List<Coupon> allCoupons = couponCustomRepository.findAll();
        return couponMapper.entityListToDtoList(allCoupons);
    }
}
