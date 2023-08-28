package az.sariyevtech.ecommerce.dto.basket;

import az.sariyevtech.ecommerce.dto.response.OrderCheckOutResponse;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {
    private Long id;
    private Double intermediatePrice;
    private Double discount;
    private Set<OrderCheckOutResponse> orderDto = new HashSet<>();
    private Long deliveryId;

}
