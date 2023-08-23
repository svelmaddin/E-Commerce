package az.sariyevtech.ecommerce.repository;

import az.sariyevtech.ecommerce.dto.orderDto.MakeOrderDto;
import az.sariyevtech.ecommerce.model.order.MakeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MakeOrderRepository extends JpaRepository<MakeOrder, Long> {
    List<MakeOrder> findByActiveTrueAndUserId(String userId);
}
