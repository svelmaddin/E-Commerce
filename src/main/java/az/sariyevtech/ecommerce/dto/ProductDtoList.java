package az.sariyevtech.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDtoList {
    private Long id;
    private String name;
    private double price;
    private boolean active;
    private StoreDto store;

}
