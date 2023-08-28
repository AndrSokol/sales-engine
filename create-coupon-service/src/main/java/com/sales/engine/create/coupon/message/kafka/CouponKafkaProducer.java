package com.sales.engine.create.coupon.message.kafka;

import com.sales.engine.create.coupon.dto.CouponDto;
import com.sales.engine.create.coupon.message.CouponProducer;
import com.sales.engine.kafka.avro.model.CouponCreatedAvroModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class CouponKafkaProducer implements CouponProducer {

    @Value("${kafka-producer-config.topic.coupon-created.name}")
    private String couponCreatedTopicName;

    private final KafkaTemplate<String, CouponCreatedAvroModel> kafkaTemplate;

    @Autowired
    public CouponKafkaProducer(KafkaTemplate<String, CouponCreatedAvroModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendCreatedCouponMessage(CouponDto coupon) {
        CouponCreatedAvroModel message = CouponCreatedAvroModel.newBuilder()
                .setCouponUuid(coupon.getUuid().toString())
                .setPromoCode(coupon.getPromoCode())
                .setCompanyName(coupon.getCompanyInfo() != null ? coupon.getCompanyInfo().companyName() : null)
                .setCompanyUuid(coupon.getCompanyUuid().toString())
                .build();

        kafkaTemplate.send(couponCreatedTopicName, message);

        log.info("Sending message: '{}' to topic: '{}'", message, couponCreatedTopicName);

        try {
            CompletableFuture<SendResult<String, CouponCreatedAvroModel>> kafkaResultFuture = kafkaTemplate
                    .send(couponCreatedTopicName, message.getCouponUuid(), message);
            kafkaResultFuture.whenComplete((result, ex) -> {
                        if (ex == null) {
                            log.info("Sent message: {} Offset : {} Topic: {}",
                                    message, result.getRecordMetadata().offset(), couponCreatedTopicName);
                        } else {
                            log.error("Unable to send message=[{}] due to : {}", message, ex.getMessage());
                        }
                    }
            );
        } catch (Exception e) {
            log.error("Error during sending message to topic: {}", couponCreatedTopicName);
            throw new KafkaException(e.getMessage());
        }
    }
}
