package yc.backend.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yc.backend.app.model.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Provider findByUsername(String username);
}

