package az.sariyevtech.ecommerce.model.order;

import az.sariyevtech.ecommerce.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class OrderModel extends BaseEntity {
    private Long customerId;
    private Long productId;
    private int count;
    private String description;
    private String deliveryAddress;
    private String deliveryTime;
    private double totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Enumerated(EnumType.STRING)
    private DeliveryLoc deliveryLocType;
}
