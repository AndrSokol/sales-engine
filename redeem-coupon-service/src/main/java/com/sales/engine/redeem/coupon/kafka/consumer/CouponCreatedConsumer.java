package com.sales.engine.redeem.coupon.kafka.consumer;

import com.sales.engine.kafka.avro.model.CouponCreatedAvroModel;
import com.sales.engine.redeem.coupon.collection.Coupon;
import com.sales.engine.redeem.coupon.redis.RedisService;
import com.sales.engine.redeem.coupon.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CouponCreatedConsumer {

    private final CouponService couponService;
    private final RedisService redisService;

    public CouponCreatedConsumer(CouponService couponService, RedisService redisService) {
        this.couponService = couponService;
        this.redisService = redisService;
    }

    @KafkaListener(
            id = "${kafka-consumer-config.coupon-created.consumer-group-id}",
            topics = "${kafka-consumer-config.coupon-created.topic-name}")
    public void receive(@Payload List<CouponCreatedAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of message received with keys {}, partitions {} and offsets {}, " +
                        "sending it to elastic: Thread id {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString(),
                Thread.currentThread().getId());

        messages.forEach(m -> {
            log.info("Saving coupon with id {}", m.getCouponUuid());
            Coupon coupon = Coupon.builder()
                    .couponUuid(m.getCouponUuid())
                    .promoCode(m.getPromoCode())
                    .companyName(m.getCompanyName())
                    .companyUuid(m.getCompanyUuid())
                    .build();

            if (redisService.getValue(coupon.getCompanyUuid()) == null) {
                couponService.save(coupon);
//                redisService.saveValue(String.valueOf(coupon.hashCode()), coupon.getCouponUuid().toString());
            }
        });
    }


}
