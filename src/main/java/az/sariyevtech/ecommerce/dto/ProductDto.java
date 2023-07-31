package az.sariyevtech.ecommerce.dto;

import az.sariyevtech.ecommerce.model.product.CategoryModel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private CategoryModel category;
    private LocalDate createDate;
    private boolean active;
    private StoreModelDto store;
    private ProductDescDto productDesc;
    private List<ProductReviewModelDto> productReview;

}
