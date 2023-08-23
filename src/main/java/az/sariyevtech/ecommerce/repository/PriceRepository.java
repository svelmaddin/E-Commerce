package az.sariyevtech.ecommerce.repository;

import az.sariyevtech.ecommerce.model.basket.TotalPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<TotalPrice, Long> {
}
