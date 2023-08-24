package az.sariyevtech.ecommerce.repository;

import az.sariyevtech.ecommerce.model.basket.BasketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<BasketModel, Long> {
    BasketModel findByCustomerId(String id);
}
