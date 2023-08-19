package az.sariyevtech.ecommerce.repository;

import az.sariyevtech.ecommerce.model.store.StoreDetails;
import az.sariyevtech.ecommerce.model.store.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreModel, Long> {

    Optional<StoreModel> findByUserId(String id);

    @Query("select s from StoreModel s where s.userId=:id")
    Optional<StoreModel> getStoreModelByUserId(String id);
}
