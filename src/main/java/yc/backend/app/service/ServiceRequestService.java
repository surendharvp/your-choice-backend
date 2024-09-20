package yc.backend.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yc.backend.app.dto.ServiceRequestDto;
import yc.backend.app.model.Bid;
import yc.backend.app.model.ServiceRequest;
import yc.backend.app.model.User;
import yc.backend.app.repository.ServiceRequestRepository;
import yc.backend.app.repository.UserRepository;

import java.util.Optional;


@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public ServiceRequest createServiceRequest(ServiceRequestDto serviceRequestDto, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setType(serviceRequestDto.getType());
        serviceRequest.setAmount(serviceRequestDto.getAmount());
        serviceRequest.setDuration(serviceRequestDto.getDuration());
        serviceRequest.setPriority(serviceRequestDto.getPriority());
        serviceRequest.setLocation(serviceRequestDto.getLocation());
        serviceRequest.setUser(user);
        serviceRequest.setStatus("OPEN");
        serviceRequestRepository.save(serviceRequest);

        // Notify service providers about the new service request
        notificationService.notifyProviders(serviceRequest);

        return serviceRequest;
    }

    public ServiceRequest getServiceRequestById(Long id) {
        Optional<ServiceRequest> optional = serviceRequestRepository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Service Request not found");
        }
        return optional.get();
    }

    @Transactional
    public void selectBid(Long serviceId, Long bidId, String username) {
        ServiceRequest serviceRequest = getServiceRequestById(serviceId);
        if (!serviceRequest.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized to select bid for this service request");
        }

        if (!serviceRequest.getStatus().equals("OPEN")) {
            throw new RuntimeException("Service request is not open for selection");
        }

        // Assume BidService has a method to get bid details
        Bid selectedBid = bidService.getBidById(bidId);
        serviceRequest.setSelectedBid(selectedBid);
        serviceRequest.setStatus("COMPLETED");
        serviceRequestRepository.save(serviceRequest);

        // Close all other bids
        bidService.closeOtherBids(serviceId, bidId);

        // Notify selected provider and others
        notificationService.notifyBidSelected(selectedBid);
    }

    @Autowired
    private BidService bidService;
}
