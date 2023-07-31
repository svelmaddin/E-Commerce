package az.sariyevtech.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDtoList {
    //storeName , name ,details, price , star
    private Long id;
    private StoreModelDto store;
    private String name;
    private ProductDescDto productDesc;
    private double price;

}
