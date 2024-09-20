package yc.backend.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yc.backend.app.model.ServiceRequest;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByLocationWithin(Double latitude, Double longitude, Double radius);
}

