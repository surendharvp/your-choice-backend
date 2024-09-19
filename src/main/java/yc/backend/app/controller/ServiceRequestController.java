package yc.backend.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService serviceRequestService;

    // Create a new service request (User posts the requirement)
    @PostMapping
    public ResponseEntity<?> createServiceRequest(@RequestBody ServiceRequestDto serviceRequestDto, 
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        ServiceRequest serviceRequest = serviceRequestService.createServiceRequest(serviceRequestDto, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceRequest);
    }

    // Get details of a service request by ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequest> getServiceRequest(@PathVariable Long id) {
        ServiceRequest serviceRequest = serviceRequestService.getServiceRequestById(id);
        return ResponseEntity.ok(serviceRequest);
    }
}
