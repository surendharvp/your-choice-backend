package com.backEnd.serviceMarketplace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backEnd.serviceMarketplace.model.Bid;
import com.backEnd.serviceMarketplace.repository.BidRepository;

@Service
public class BidService {
    private final BidRepository bidRepository;

    public BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public Bid createBid(Bid bid) {
        return bidRepository.save(
            new Bid(
                bid.id(),
                bid.amount(),
                bid.serviceRequest(),
                bid.serviceProvider(),
                Bid.Status.PENDING
            )
        );
    }

    public List<Bid> getBidsForServiceRequest(Long serviceRequestId) {
        return bidRepository.findByServiceRequestId(serviceRequestId);
    }
}
