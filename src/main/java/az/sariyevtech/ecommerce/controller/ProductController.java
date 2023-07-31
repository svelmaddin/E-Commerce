package az.sariyevtech.ecommerce.controller;

import az.sariyevtech.ecommerce.dto.ProductDto;
import az.sariyevtech.ecommerce.dto.ProductDtoList;
import az.sariyevtech.ecommerce.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/product")
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
        var product = productService.getById(Id);
        return ResponseEntity.ok(product);
    }
}
