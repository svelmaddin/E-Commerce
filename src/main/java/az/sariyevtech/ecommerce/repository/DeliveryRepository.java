package az.sariyevtech.ecommerce.repository;

import az.sariyevtech.ecommerce.model.delivery.DeliveryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryModel , Long> {
}
