package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.dto.ProductDto;
import az.sariyevtech.ecommerce.dto.ProductDtoList;
import az.sariyevtech.ecommerce.dto.request.ProductCreateRequest;

import java.util.List;

public interface ProductService {
    List<ProductDtoList> getAllProducts();
    ProductDto viewProduct(Long id);
    List<ProductDtoList> getStoreProducts();
    ProductDto updateProduct(Long productId, ProductDto dto);
    void setProductActiveStatus(Long id, Boolean status);
    void createProduct(ProductCreateRequest request);
    void deleteProduct(Long id);
}
