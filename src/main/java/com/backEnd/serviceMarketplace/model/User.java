package com.backEnd.serviceMarketplace.model;

import jakarta.persistence.*;
public record User (
	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    Long id,
	    String username,
	    String email,
	    String password,
	    String role // "USER" or "SERVICE_PROVIDER"
) {}
