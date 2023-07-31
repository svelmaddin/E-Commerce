package az.sariyevtech.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductReviewModelDto {
    private Long id;
    private Boolean oneStar;
    private Boolean twoStar;
    private Boolean threeStar;
    private Boolean fourStar;
    private Boolean fiveStar;
    private String reviewText;
}
