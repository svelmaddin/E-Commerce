package az.sariyevtech.ecommerce.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductReviewDto {
    private Long id;
    private Integer oneStar;
    private Integer twoStar;
    private Integer threeStar;
    private Integer fourStar;
    private Integer fiveStar;
    private String reviewText;
}
