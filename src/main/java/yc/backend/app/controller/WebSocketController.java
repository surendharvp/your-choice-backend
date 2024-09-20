package yc.backend.app.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import yc.backend.app.model.Bid;
import yc.backend.app.model.ServiceRequest;

@RestController
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

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
