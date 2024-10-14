package com.backEnd.serviceMarketplace.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.backEnd.serviceMarketplace.model.ServiceRequest;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyServiceProviders(ServiceRequest serviceRequest) {
        messagingTemplate.convertAndSend("/topic/service-requests", serviceRequest);
    }
}
