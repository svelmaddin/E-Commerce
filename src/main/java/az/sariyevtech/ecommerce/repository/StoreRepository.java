package az.sariyevtech.ecommerce.repository;

import az.sariyevtech.ecommerce.model.store.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreModel, Long> {
    Optional<StoreModel> findByUserId(String id);
}
