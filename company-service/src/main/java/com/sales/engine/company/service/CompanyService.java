package com.sales.engine.company.service;

import com.sales.engine.company.dto.CompanyDto;
import com.sales.engine.company.dto.CreateCompanyRequest;

import java.util.UUID;

public interface CompanyService {
    CompanyDto getByUuid(UUID uuid);

    CompanyDto createCompany(CreateCompanyRequest createCompanyRequest);
}
