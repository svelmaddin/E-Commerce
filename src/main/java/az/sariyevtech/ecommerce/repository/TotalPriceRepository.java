package az.sariyevtech.ecommerce.repository;

import az.sariyevtech.ecommerce.model.basket.TotalPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface TotalPriceRepository extends JpaRepository<TotalPrice, Long> {
   Optional<TotalPrice> findByBasketId(Long id);
}
