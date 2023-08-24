package az.sariyevtech.ecommerce.dto.orderDto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakeOrderDto {
    private Long id;
    private Long productId;
    private String name;
    private String userId;
    private int count;
    private Double totalPrice;
    private Double totalDiscount;
    private boolean active;
}
