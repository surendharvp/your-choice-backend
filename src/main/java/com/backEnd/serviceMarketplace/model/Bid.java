package com.backEnd.serviceMarketplace.model;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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