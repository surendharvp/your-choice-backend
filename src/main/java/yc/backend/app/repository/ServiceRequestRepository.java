package yc.backend.app.repository;

import com.example.platform.models.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByLocationWithin(Double latitude, Double longitude, Double radius);
}

