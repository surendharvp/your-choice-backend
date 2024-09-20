package yc.backend.app.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BidRequest {
    private Double amount;
    private String review; // Optional
    private Double rating; // Provider's rating

    // Getters and Setters
    // ...
}

