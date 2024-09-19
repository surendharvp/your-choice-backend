package yc.backend.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services/{serviceId}/bids")
public class BidController {

    @Autowired
    private BidService bidService;

    // Service Provider submits a bid for a service
    @PostMapping
    public ResponseEntity<?> submitBid(@PathVariable Long serviceId,
                                       @RequestBody BidRequest bidRequest, 
                                       @AuthenticationPrincipal UserDetails userDetails) {
        Bid bid = bidService.submitBid(serviceId, bidRequest, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(bid);
    }

    // Get all bids for a specific service (for the User to view)
    @GetMapping
    public ResponseEntity<List<Bid>> getAllBidsForService(@PathVariable Long serviceId) {
        List<Bid> bids = bidService.getAllBidsForService(serviceId);
        return ResponseEntity.ok(bids);
    }

    // User selects a service provider bid (marks service as complete)
    @PostMapping("/select")
    public ResponseEntity<?> selectBid(@PathVariable Long serviceId, 
                                       @RequestParam Long bidId, 
                                       @AuthenticationPrincipal UserDetails userDetails) {
        serviceRequestService.selectBid(serviceId, bidId, userDetails);
        return ResponseEntity.ok("Bid selected, service request completed");
    }
}
