package yc.backend.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Notify all service providers about a new service request
    public void notifyProviders(ServiceRequest serviceRequest) {
        // Send real-time notification to service providers within the location limit
        messagingTemplate.convertAndSend("/topic/requests", serviceRequest);
    }

    // Notify user when a new bid is placed
    public void notifyUser(Long userId, Bid bid) {
        messagingTemplate.convertAndSendToUser(userId.toString(), "/queue/bids", bid);
    }
}
