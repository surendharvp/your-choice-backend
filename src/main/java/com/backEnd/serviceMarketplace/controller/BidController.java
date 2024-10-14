package com.backEnd.serviceMarketplace.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backEnd.serviceMarketplace.model.Bid;
import com.backEnd.serviceMarketplace.service.BidService;

import java.util.List;

@RestController
@RequestMapping("/api/bids")
public class BidController {
    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping
    public ResponseEntity<Bid> createBid(@RequestBody Bid bid) {
        return ResponseEntity.ok(bidService.createBid(bid));
    }

    @GetMapping("/service-request/{serviceRequestId}")
    public ResponseEntity<List<Bid>> getBidsForServiceRequest(@PathVariable Long serviceRequestId) {
        return ResponseEntity.ok(bidService.getBidsForServiceRequest(serviceRequestId));
    }
}
