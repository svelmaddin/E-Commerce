package az.sariyevtech.ecommerce.dto.response;

import lombok.Builder;

import java.util.Set;

@Builder
public class BasketResponse {
    private Long id;
    private Double intermediatePrice;
    private Double discount;
    private Set<OrderCheckOutResponse> orderDto;
}
