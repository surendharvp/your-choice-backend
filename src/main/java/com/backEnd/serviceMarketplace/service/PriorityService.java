package com.backEnd.serviceMarketplace.service;
import org.springframework.stereotype.Service;

import com.backEnd.serviceMarketplace.model.ServiceRequest;

@Service
public class PriorityService {
    public int calculatePriorityScore(ServiceRequest request) {
        return switch (request) {
            case ServiceRequest(var type, var amount, var duration, var priority, var location, var status, var user)
                when priority.equalsIgnoreCase("high") -> 10;
            case ServiceRequest(var type, var amount, var duration, var priority, var location, var status, var user)
                when priority.equalsIgnoreCase("medium") -> 5;
            case ServiceRequest(var type, var amount, var duration, var priority, var location, var status, var user)
                when priority.equalsIgnoreCase("low") -> 1;
            default -> 0;
        };
    }
}