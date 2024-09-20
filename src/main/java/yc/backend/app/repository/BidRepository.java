package yc.backend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yc.backend.app.model.Bid;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByServiceRequestId(Long serviceRequestId);
    List<Bid> findByServiceRequestIdAndProviderId(Long serviceRequestId, Long providerId);
}

