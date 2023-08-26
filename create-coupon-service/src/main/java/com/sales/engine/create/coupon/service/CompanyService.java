package com.sales.engine.create.coupon.service;

import com.sales.engine.create.coupon.dto.CompanyResponse;

import java.util.UUID;

public interface CompanyService {
    CompanyResponse findCompanyByUuid(UUID companyUuid);
}
