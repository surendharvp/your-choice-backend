package com.backEnd.serviceMarketplace.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.backEnd.serviceMarketplace.model.ServiceRequest;

@Service
public class KafkaService {
    private static final String TOPIC = "service-requests";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendServiceRequest(ServiceRequest serviceRequest) {
        kafkaTemplate.send(TOPIC, serviceRequest);
    }
}