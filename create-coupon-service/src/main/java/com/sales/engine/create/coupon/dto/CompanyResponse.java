package com.sales.engine.create.coupon.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CompanyResponse(UUID uuid,
                              String companyName,
                              LocalDateTime createdAt) {
}
