package az.sariyevtech.ecommerce.dto;

import az.sariyevtech.ecommerce.model.product.SizeModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductDescDto {
    private String color;
    private String material;
    private String description;
    private Integer productStock;
    @Enumerated(EnumType.STRING)
    private SizeModel productSize;
    private Integer oneStar;
    private Integer twoStar;
    private Integer threeStar;
    private Integer fourStar;
    private Integer fiveStar;
    private String reviewText;
}
