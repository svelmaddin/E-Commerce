package az.sariyevtech.ecommerce.dto.request;

import az.sariyevtech.ecommerce.model.order.DeliveryLoc;
import az.sariyevtech.ecommerce.model.order.PaymentType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderCreateRequest {
    private Long productId;
    private int count;
    private String description;
    private String deliveryAddress;
    private String deliveryTime;
    private PaymentType paymentType;
    private DeliveryLoc deliveryLocType;
}
