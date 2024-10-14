package com.backEnd.serviceMarketplace.model;
import com.backEnd.serviceMarketplace.entity.ServiceRequest;
import com.backEnd.serviceMarketplace.entity.User;

import jakarta.persistence.*;
public record Bid(
	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	    Long id,
	    Double amount,
	    @ManyToOne @JoinColumn(name = "service_request_id") 
	    ServiceRequest serviceRequest,
	    @ManyToOne @JoinColumn(name = "service_provider_id") 
	    User serviceProvider,
	    @Enumerated(EnumType.STRING) Status status
	) {
	    public enum Status {
	        PENDING, ACCEPTED, REJECTED
	    }
	}