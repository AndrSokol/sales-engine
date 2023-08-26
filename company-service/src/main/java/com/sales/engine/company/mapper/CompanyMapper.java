package com.sales.engine.company.mapper;

import com.sales.engine.company.dto.CompanyDto;
import com.sales.engine.company.entity.Company;

import java.util.List;


public interface CompanyMapper {
    CompanyDto companyToDto(Company company);
    Company dtoTocompany(CompanyDto companyDto);
    List<CompanyDto> companyListToDto(List<Company> companyList);
    List<Company> dtoListToCompanyList(List<CompanyDto> companyList);
}
