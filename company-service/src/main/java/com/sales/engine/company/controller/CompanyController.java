package com.sales.engine.company.controller;

import com.sales.engine.company.dto.CreateCompanyRequest;
import com.sales.engine.company.dto.CompanyDto;
import com.sales.engine.company.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/company")
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{companyId}")
    public CompanyDto getCompanyById(@PathVariable String companyId) {
        log.info("Retrieve company info by uuid: {}", companyId);
        UUID uuid = UUID.fromString(companyId);
        CompanyDto companyDto = companyService.getByUuid(uuid);
        return companyDto;
    }

    @PostMapping
    public CompanyDto createCompany(@RequestBody CreateCompanyRequest createCompanyRequest) {
        CompanyDto companyDto = companyService.createCompany(createCompanyRequest);
        return companyDto;
    }
}
