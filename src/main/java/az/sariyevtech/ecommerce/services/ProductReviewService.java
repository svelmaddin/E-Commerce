package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.dto.ProductReviewDto;

import java.util.List;

public interface ProductReviewService {
    //give star;
    //give feadback;

    List<ProductReviewDto> getAllReview(Long productId);

    ProductReviewDto giveStar(int star, Long productId);

    ProductReviewDto giveReviewText(String review, Long productId);

}
