package com.sales.engine.company.mapper.impl;

import com.sales.engine.company.dto.CompanyDto;
import com.sales.engine.company.entity.Company;
import com.sales.engine.company.mapper.CompanyMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyMapperImpl implements CompanyMapper {
    @Override
    public CompanyDto companyToDto(Company company) {
        return CompanyDto.builder()
                .uuid(company.getUuid())
                .companyName(company.getCompanyName())
                .createdAt(company.getCreatedAt())
                .build();
    }

    @Override
    public Company dtoTocompany(CompanyDto companyDto) {
        return Company.builder()
                .uuid(companyDto.uuid())
                .companyName(companyDto.companyName())
                .createdAt(companyDto.createdAt())
                .build();
    }

    @Override
    public List<CompanyDto> companyListToDto(List<Company> companyList) {
        return companyList.stream().map(this::companyToDto).toList();
    }

    @Override
    public List<Company> dtoListToCompanyList(List<CompanyDto> companyList) {
        return companyList.stream().map(this::dtoTocompany).toList();
    }
}
