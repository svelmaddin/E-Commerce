package az.sariyevtech.ecommerce.repository;

import az.sariyevtech.ecommerce.model.order.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    List<OrderModel> findByCustomerId(String id);

    Optional<OrderModel> findByIdAndCustomerId(Long id, String userId);
}
