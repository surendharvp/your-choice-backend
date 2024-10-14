package com.backEnd.serviceMarketplace.model;
import jakarta.persistence.*;
public record ServiceRequest(
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
	    String type,
	    Double amount,
	    String duration,
	    String priority,
	    String location,
	    @Enumerated(EnumType.STRING) Status status,
	    @ManyToOne @JoinColumn(name = "user_id") User user)
{

    public enum Status {
        OPEN, IN_PROGRESS, COMPLETED
    }

}
