package az.sariyevtech.ecommerce.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCheckOutResponse {
    private Long productId;
    private String name;
    private int count;
    private Double price;
}
