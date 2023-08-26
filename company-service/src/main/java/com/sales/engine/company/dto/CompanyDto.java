package com.sales.engine.company.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CompanyDto(UUID uuid,
                         String companyName,
                         LocalDateTime createdAt) {
}
