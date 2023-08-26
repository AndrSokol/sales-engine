package com.sales.engine.company.service.impl;

import com.sales.engine.company.dto.CompanyDto;
import com.sales.engine.company.dto.CreateCompanyRequest;
import com.sales.engine.company.entity.Company;
import com.sales.engine.company.exception.CompanyAlreadyExistsException;
import com.sales.engine.company.repository.CompanyRepository;
import com.sales.engine.company.service.CompanyService;
import com.sales.engine.company.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Override
    public CompanyDto getByUuid(UUID uuid) {
        Company company = companyRepository.getReferenceById(uuid);
        return companyMapper.companyToDto(company);
    }

    @Override
    public CompanyDto createCompany(CreateCompanyRequest createCompanyRequest) {

        Optional<Company> foundCompanyOptional = companyRepository
                .findByCompanyName(createCompanyRequest.companyName());

        if (foundCompanyOptional.isEmpty()) {
            Company newCompany = Company.builder()
                    .uuid(UUID.randomUUID())
                    .companyName(createCompanyRequest.companyName())
                    .build();

            Company savedCompany = companyRepository.save(newCompany);
            return companyMapper.companyToDto(savedCompany);
        } else {
            throw new CompanyAlreadyExistsException(String.format("Company with name %s already exists"));
        }
    }
}
