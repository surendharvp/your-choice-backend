package yc.backend.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import yc.backend.app.model.Bid;
import yc.backend.app.model.ServiceRequest;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Notify service providers about a new service request
    public void notifyProviders(ServiceRequest serviceRequest) {
        // You might want to filter providers based on location
        // For simplicity, we're broadcasting to all subscribed providers
        messagingTemplate.convertAndSend("/topic/requests", serviceRequest);
    }

    // Notify user about a new bid
    public void notifyUser(Long userId, Bid bid) {
        messagingTemplate.convertAndSendToUser(userId.toString(), "/queue/bids", bid);
    }

    // Notify selected provider that their bid has been selected
    public void notifyBidSelected(Bid bid) {
        messagingTemplate.convertAndSendToUser(bid.getProvider().getId().toString(), "/queue/selection", bid);
    }

    // Notify provider that their bid has been closed
    public void notifyBidClosed(Bid bid) {
        messagingTemplate.convertAndSendToUser(bid.getProvider().getId().toString(), "/queue/bid-closed", bid);
    }
}
