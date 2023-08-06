package az.sariyevtech.ecommerce.repository;

import az.sariyevtech.ecommerce.model.product.ProductReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReviewModel, Long> {
    ProductReviewModel findByProductId(Long productId);
}
