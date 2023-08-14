package az.sariyevtech.ecommerce.dto.productDto;

import az.sariyevtech.ecommerce.dto.storeDto.StoreDto;
import az.sariyevtech.ecommerce.model.product.CategoryModel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private CategoryModel category;
    private final LocalDateTime createDate;
    private boolean active;
    private StoreDto store;
    private ProductDescDto productDesc;
    private final List<ProductReviewDto> productReview;

}
