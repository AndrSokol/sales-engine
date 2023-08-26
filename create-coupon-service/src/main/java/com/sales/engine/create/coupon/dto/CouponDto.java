package com.sales.engine.create.coupon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto implements Serializable {
    UUID uuid;
    String promoCode;
    UUID companyUuid;
    LocalDateTime createdAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    CompanyResponse companyInfo;
}
