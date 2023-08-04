package az.sariyevtech.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductReviewDto {
    private Long id;
    private Boolean oneStar;
    private Boolean twoStar;
    private Boolean threeStar;
    private Boolean fourStar;
    private Boolean fiveStar;
    private String reviewText;
}
