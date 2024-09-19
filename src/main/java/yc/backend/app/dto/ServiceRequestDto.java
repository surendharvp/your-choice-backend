package yc.backend.app.dto;


public class ServiceRequestDto {
    private String type;
    private Double amount;
    private Integer duration; // in hours or days
    private String priority; // HIGH, MEDIUM, LOW
    private String location; // e.g., "latitude,longitude"

    // Getters and Setters
    // ...
}

