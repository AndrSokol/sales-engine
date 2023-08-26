package com.sales.engine.create.coupon;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.sales.engine.create.coupon")
public class CreateCouponServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreateCouponServiceApplication.class, args);
    }

//    @Bean
//    public SpringLiquibase liquibase(DataSource dataSource) {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setDataSource(dataSource);
//        liquibase.setChangeLog("classpath:db/changelog/changelog-master.xml");
//        return liquibase;
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

