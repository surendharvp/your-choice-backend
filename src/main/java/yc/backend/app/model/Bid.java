package yc.backend.app.model;

import javax.persistence.*;

@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String review; // Optional review or comment
    private Double rating; // Provider's rating

    private String status; // OPEN, CLOSED, SELECTED

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "service_request_id", nullable = false)
    private ServiceRequest serviceRequest;

    // Getters and Setters
    // ...
}

