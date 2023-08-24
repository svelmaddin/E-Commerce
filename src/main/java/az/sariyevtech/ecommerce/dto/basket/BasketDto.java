package az.sariyevtech.ecommerce.dto.basket;

import az.sariyevtech.ecommerce.dto.orderDto.MakeOrderDto;
import az.sariyevtech.ecommerce.dto.response.OrderCheckOutResponse;
import lombok.*;
import org.springframework.stereotype.Service;

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

}
