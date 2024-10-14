package com.backEnd.serviceMarketplace.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backEnd.serviceMarketplace.entity.ServiceRequest;
import com.backEnd.serviceMarketplace.service.ServiceRequestService;

@RestController
@RequestMapping("/api/service-requests")
public class ServiceRequestController {
    private final ServiceRequestService serviceRequestService;

    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @PostMapping
    public ResponseEntity<ServiceRequest> createServiceRequest(@RequestBody ServiceRequest serviceRequest) {
        return ResponseEntity.ok(serviceRequestService.createServiceRequest(serviceRequest));
    }

    @GetMapping("/open")
    public ResponseEntity<List<ServiceRequest>> getOpenServiceRequests() {
        return ResponseEntity.ok(serviceRequestService.getOpenServiceRequests());
    }
}