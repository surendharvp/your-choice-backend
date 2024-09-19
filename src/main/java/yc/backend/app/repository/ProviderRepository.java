package yc.backend.app.repository;

import com.example.platform.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Provider findByUsername(String username);
}

