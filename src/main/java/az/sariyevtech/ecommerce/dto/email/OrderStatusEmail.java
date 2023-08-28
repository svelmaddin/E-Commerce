package az.sariyevtech.ecommerce.dto.email;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusEmail {
    private Long orderId;
    private List<String> productNames;
    private String deliveryAddress;
    private Double totalPrice;
    private Double discount;
    private String orderStatus;
    private String customerId;
    private String customerName;

}
