package com.sales.engine.company.service.impl;

import com.sales.engine.company.dto.CompanyDto;
import com.sales.engine.company.dto.CreateCompanyRequest;
import com.sales.engine.company.entity.Company;
import com.sales.engine.company.exception.CompanyAlreadyExistsException;
import com.sales.engine.company.exception.CompanyNotFoundException;
import com.sales.engine.company.mapper.CompanyMapper;
import com.sales.engine.company.repository.CompanyRepository;
import com.sales.engine.company.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
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
        Optional<Company> companyOptional = companyRepository.findById(uuid);
        if (companyOptional.isEmpty()) {
            throw new CompanyNotFoundException(String.format("Company with id: %s not found", uuid));
        }
        return companyMapper.companyToDto(companyOptional.get());
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
            throw new CompanyAlreadyExistsException(
                    String.format("Company with name %s already exists", createCompanyRequest.companyName()));
        }
    }
}
