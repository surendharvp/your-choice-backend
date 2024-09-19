package yc.backend.app.service;

import com.example.platform.dto.BidRequest;
import com.example.platform.models.Bid;
import com.example.platform.models.Provider;
import com.example.platform.models.ServiceRequest;
import com.example.platform.repositories.BidRepository;
import com.example.platform.repositories.ProviderRepository;
import com.example.platform.repositories.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public Bid submitBid(Long serviceId, BidRequest bidRequest, String username) {
        ServiceRequest serviceRequest = serviceRequestRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service Request not found"));

        if (!serviceRequest.getStatus().equals("OPEN")) {
            throw new RuntimeException("Cannot bid on a closed service request");
        }

        Provider provider = providerRepository.findByUsername(username);
        if (provider == null) {
            throw new RuntimeException("Provider not found");
        }

        // Check if provider has already bid on this service request
        List<Bid> existingBids = bidRepository.findByServiceRequestIdAndProviderId(serviceId, provider.getId());
        if (!existingBids.isEmpty()) {
            throw new RuntimeException("Provider has already bid on this service request");
        }

        Bid bid = new Bid();
        bid.setAmount(bidRequest.getAmount());
        bid.setReview(bidRequest.getReview());
        bid.setRating(bidRequest.getRating());
        bid.setProvider(provider);
        bid.setServiceRequest(serviceRequest);
        bid.setStatus("OPEN");
        bidRepository.save(bid);

        // Notify the user about the new bid
        notificationService.notifyUser(serviceRequest.getUser().getId(), bid);

        return bid;
    }

    public List<Bid> getAllBidsForService(Long serviceId) {
        return bidRepository.findByServiceRequestId(serviceId);
    }

    public Bid getBidById(Long bidId) {
        return bidRepository.findById(bidId)
                .orElseThrow(() -> new RuntimeException("Bid not found"));
    }

    @Transactional
    public void closeOtherBids(Long serviceId, Long selectedBidId) {
        List<Bid> bids = bidRepository.findByServiceRequestId(serviceId);
        for (Bid bid : bids) {
            if (!bid.getId().equals(selectedBidId)) {
                bid.setStatus("CLOSED");
                bidRepository.save(bid);

                // Notify the provider that their bid is closed
                notificationService.notifyBidClosed(bid);
            }
        }
    }
}

