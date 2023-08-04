package az.sariyevtech.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDtoList {
    //storeName , name ,details, price , star
    private Long id;
    private String name;
    private double price;
    private StoreDto store;
    private ProductDescDto productDesc;

}
