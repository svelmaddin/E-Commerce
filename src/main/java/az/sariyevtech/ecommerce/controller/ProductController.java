package az.sariyevtech.ecommerce.controller;

import az.sariyevtech.ecommerce.dto.productDto.ProductDto;
import az.sariyevtech.ecommerce.dto.productDto.ProductDtoList;
import az.sariyevtech.ecommerce.dto.request.ProductCreateRequest;
import az.sariyevtech.ecommerce.services.impl.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDtoList>> getAll() {
        var product = productServiceImpl.getAllProducts();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long Id) {
        return ResponseEntity.ok(productServiceImpl.viewProduct(Id));
    }

    @GetMapping("/userProducts")
    public ResponseEntity<List<ProductDtoList>> getStoreProducts() {
        return ResponseEntity.ok(productServiceImpl.getStoreProducts());
    }

    @PutMapping("editProduct/{productId}")
    public ResponseEntity<ProductDto> editProduct(@PathVariable Long productId,
                                                  @RequestBody ProductDto dto) {
        return ResponseEntity.ok(productServiceImpl.updateProduct(productId, dto));
    }

    @PatchMapping("/editStatus/{id}")
    public ResponseEntity<String> changeProductActiveStatus(@PathVariable Long id,
                                                          @RequestParam Boolean status) {
        productServiceImpl.setProductActiveStatus(id, status);
        return ResponseEntity.ok("Products active status updated successfully.");
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Void> createProduct(@RequestBody ProductCreateRequest dto) {
        productServiceImpl.createProduct(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productServiceImpl.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
