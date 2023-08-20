package az.sariyevtech.ecommerce.dto.orderDto;

import az.sariyevtech.ecommerce.model.order.DeliveryLoc;
import az.sariyevtech.ecommerce.model.order.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private String customerId;
    private Long productId;
    private int count;
    private LocalDateTime createAt;
    private String description;
    private String deliveryAddress;
    private String deliveryTime;
    private double totalPrice;
    private PaymentType paymentType;
    private DeliveryLoc deliveryLocType;
}
