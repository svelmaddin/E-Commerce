package az.sariyevtech.ecommerce.dto.request;

import lombok.Getter;

@Getter
public class MakeOrder {
    private boolean gift;
    private Long deliveryId;
}
