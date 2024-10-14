package com.backEnd.serviceMarketplace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backEnd.serviceMarketplace.repository.ServiceRequestRepository;

@Service
public class ServiceRequestService {
    private final ServiceRequestRepository serviceRequestRepository;
    private final NotificationService notificationService;
    private final KafkaService kafkaService;

    public ServiceRequestService(ServiceRequestRepository serviceRequestRepository,
                                 NotificationService notificationService,
                                 KafkaService kafkaService) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.notificationService = notificationService;
        this.kafkaService = kafkaService;
    }

    public ServiceRequest createServiceRequest(ServiceRequest serviceRequest) {
        var savedRequest = serviceRequestRepository.save(
            new ServiceRequest(
                serviceRequest.id(),
                serviceRequest.type(),
                serviceRequest.amount(),
                serviceRequest.duration(),
                serviceRequest.priority(),
                serviceRequest.location(),
                ServiceRequest.Status.OPEN,
                serviceRequest.user()
            )
        );
        notificationService.notifyServiceProviders(savedRequest);
        kafkaService.sendServiceRequest(savedRequest);
        return savedRequest;
    }

    public List<ServiceRequest> getOpenServiceRequests() {
        return serviceRequestRepository.findByStatus(ServiceRequest.Status.OPEN);
    }
}

