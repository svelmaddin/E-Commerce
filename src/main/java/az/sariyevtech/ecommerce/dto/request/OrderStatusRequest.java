package az.sariyevtech.ecommerce.dto.request;

import lombok.*;

@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusRequest {
    private String customerId;
    private String storeId;
    private Long orderId;
    private String details;
    private Double price;
    private String orderStatus;

}
