package az.sariyevtech.ecommerce.dto.request;

import az.sariyevtech.ecommerce.dto.productDto.ProductDescDto;
import az.sariyevtech.ecommerce.model.product.CategoryModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductCreateRequest {
    private String name;
    private Double price;
    private CategoryModel category;
    private ProductDescDto productDesc;

}
