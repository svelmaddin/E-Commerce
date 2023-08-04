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
    private Double price;
    private CategoryModel category;
    private final LocalDate createDate;
    private boolean active;
    private StoreDto store;
    public ProductDescDto productDesc;
    public final List<ProductReviewDto> productReview;

}
