package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.dto.ProductDto;
import az.sariyevtech.ecommerce.dto.ProductDtoConverter;
import az.sariyevtech.ecommerce.dto.ProductDtoList;
import az.sariyevtech.ecommerce.model.product.ProductModel;
import az.sariyevtech.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductDtoConverter converter ;

    public ProductService(ProductRepository repository, ProductDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<ProductDtoList> getAllProducts(){
        ProductModel fromDb = new ProductModel();
        //storeName , name ,details, price , star
        return repository.findAll().stream().map(converter::convertForList).collect(Collectors.toList());
    }
    public ProductDto getById(Long id){
        return converter.convert(repository.findById(id).orElseThrow());
    }
}
