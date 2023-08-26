package com.sales.engine.company.repository;

import com.sales.engine.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<Company> findByCompanyName(String companyName);
}
