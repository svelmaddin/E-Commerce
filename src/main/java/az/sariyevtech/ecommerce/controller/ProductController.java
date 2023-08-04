package az.sariyevtech.ecommerce.controller;

import az.sariyevtech.ecommerce.dto.ProductDto;
import az.sariyevtech.ecommerce.dto.ProductDtoList;
import az.sariyevtech.ecommerce.dto.request.ProductCreateRequest;
import az.sariyevtech.ecommerce.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDtoList>> getAll() {
        var product = productService.getAllProducts();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long Id) {
        return ResponseEntity.ok(productService.viewProduct(Id));
    }

    @GetMapping("/userProducts")
    public ResponseEntity<List<ProductDtoList>> getStoreProducts() {
        return ResponseEntity.ok(productService.getStoreProducts());
    }

    @PutMapping("editProduct/{productId}")
    public ResponseEntity<ProductDto> editProduct(@PathVariable Long productId,
                                                  @RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.updateProduct(productId, dto));
    }

    @PatchMapping("/editStatus/{id}")
    public ResponseEntity<Void> changeProductActiveStatus(@PathVariable Long id, Boolean status) {
        productService.setProductActiveStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Void> createProduct(@RequestBody ProductCreateRequest dto) {
        productService.createProduct(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
