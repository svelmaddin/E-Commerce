package az.sariyevtech.ecommerce.repository;

import az.sariyevtech.ecommerce.model.store.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreModel, Long> {
    StoreModel findByUserId(Long id);
}
