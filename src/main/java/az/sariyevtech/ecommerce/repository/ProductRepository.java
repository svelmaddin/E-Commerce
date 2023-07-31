package az.sariyevtech.ecommerce.repository;

import az.sariyevtech.ecommerce.model.product.ProductModel;
import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel , Long> {
}
