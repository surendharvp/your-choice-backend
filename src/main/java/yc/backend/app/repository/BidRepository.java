package yc.backend.app.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByServiceRequestId(Long serviceRequestId);
    List<Bid> findByServiceRequestIdAndProviderId(Long serviceRequestId, Long providerId);
}

