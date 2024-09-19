package yc.backend.app.model;

import java.time.LocalDateTime;

@Entity
@Table(name = "service_requests")
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private Double amount;
    private Integer duration; // in hours or days
    private String priority; // HIGH, MEDIUM, LOW
    private String location; // Could be a geospatial type

    private String status; // OPEN, COMPLETED, etc.

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "selected_bid_id")
    private Bid selectedBid;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
    // ...

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

