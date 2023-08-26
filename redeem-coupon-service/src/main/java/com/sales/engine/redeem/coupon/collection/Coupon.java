package com.sales.engine.redeem.coupon.collection;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode
@Builder
@Getter
@Setter
@Document(collection = "couponCollection")
public class Coupon implements Serializable {

    @Version
    private Long version;

    private String couponUuid;
    private String promoCode;
    private String companyUuid;
    private String companyName;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}