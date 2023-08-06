package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.ProductDto;
import az.sariyevtech.ecommerce.dto.ProductReviewDto;
import az.sariyevtech.ecommerce.model.product.ProductModel;
import az.sariyevtech.ecommerce.model.product.ProductReviewModel;
import az.sariyevtech.ecommerce.repository.ProductReviewRepository;
import az.sariyevtech.ecommerce.services.ProductReviewService;
import az.sariyevtech.ecommerce.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {
    private final ProductReviewRepository productReviewRepository;
    private final ProductService productService;

    public ProductReviewServiceImpl(ProductReviewRepository productReviewRepository, ProductService productService) {
        this.productReviewRepository = productReviewRepository;
        this.productService = productService;
    }

    @Override
    public List<ProductReviewDto> getAllReview(Long productId) {
        return null;
    }

    @Override
    public ProductReviewDto giveStar(int star, Long productId) {
        return null;
    }

    @Override
    public ProductReviewDto giveReviewText(String review, Long productId) {
        return null;
    }

    public Character getStarReview(Long productId) {
        ProductReviewModel review = productReviewRepository.findByProductId(productId);
        int one = review.getOneStar();
        int two = review.getTwoStar();
        int tree = review.getThreeStar();
        int four = review.getFourStar();
        int five = review.getFiveStar();
        int countStar = one + two + tree + four + five;
        return null;
    }

    //0-20% - 1
    //20-40 - 2
    //40-60 - 3
    //60-80 - 4
    //80-100 -5
}
