package az.sariyevtech.ecommerce.dto.request;

import az.sariyevtech.ecommerce.dto.ProductDescDto;
import az.sariyevtech.ecommerce.model.product.CategoryModel;
import az.sariyevtech.ecommerce.model.product.ProductDescription;
import az.sariyevtech.ecommerce.model.product.ProductModel;
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
