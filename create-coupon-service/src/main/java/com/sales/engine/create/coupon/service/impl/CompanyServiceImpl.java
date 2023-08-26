package com.sales.engine.create.coupon.service.impl;

import com.sales.engine.create.coupon.dto.CompanyResponse;
import com.sales.engine.create.coupon.service.CompanyService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    // TODO: Move to application.yml file
    private final String COMPANY_SERVICE_URL = "http://localhost:8093/company/";

    private final RestTemplate restTemplate;

    public CompanyServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
//    @CircuitBreaker(name = "getCompanyByUuid", fallbackMethod = "fallbackCircuitBreakerGetCompany")
    @Retry(name = "companyService", fallbackMethod = "fallbackRetryGetCompany")
    public CompanyResponse findCompanyByUuid(UUID companyUuid) {
        log.info("Call to company service...");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer yourAccessToken");
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<CompanyResponse> response = restTemplate.exchange(String.format(COMPANY_SERVICE_URL + "{companyUuid}"),
                HttpMethod.GET,
                entity,
                CompanyResponse.class,
                companyUuid
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Response Body: " + response.getBody());
        } else {
            log.error("Request failed with status code: {}", response.getStatusCode());
        }

        if (response.getStatusCode().is4xxClientError()) {
            log.error("Company with uuid {} was not found", companyUuid);
        }

        return response.getBody();
    }

//    public Optional<CompanyResponse> fallbackCircuitBreakerGetCompany(Throwable ex) {
//        log.error("Company service unavailable. Exception: {}", ex.getMessage());
//        return Optional.empty();
//    }

    public CompanyResponse fallbackRetryGetCompany(Exception ex) {
        log.error("Attempts threshold has been exceeded. Company service unavailable: {}", ex.getMessage());
        return null;
    }
}
