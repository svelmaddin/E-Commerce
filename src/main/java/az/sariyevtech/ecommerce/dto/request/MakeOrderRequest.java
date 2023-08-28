package az.sariyevtech.ecommerce.dto.request;

import lombok.Getter;

@Getter
public class MakeOrderRequest {
    private boolean gift;
    private Long deliveryId;
}
