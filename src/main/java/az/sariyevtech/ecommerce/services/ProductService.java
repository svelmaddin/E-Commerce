package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.dto.ProductDto;
import az.sariyevtech.ecommerce.dto.ProductDtoConverter;
import az.sariyevtech.ecommerce.dto.ProductDtoList;
import az.sariyevtech.ecommerce.model.product.ProductDescription;
import az.sariyevtech.ecommerce.model.product.ProductModel;
import az.sariyevtech.ecommerce.model.store.StoreModel;
import az.sariyevtech.ecommerce.repository.ProductRepository;
import az.sariyevtech.ecommerce.response.TokenResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductDtoConverter converter;
    private final TokenResponse tokenResponse;

    public ProductService(ProductRepository repository, ProductDtoConverter converter,
                          TokenResponse tokenResponse) {
        this.repository = repository;
        this.converter = converter;
        this.tokenResponse = tokenResponse;
    }

    //forUsers and salesManager
    public List<ProductDtoList> getAllProducts() {
        return repository.findAllByActive(true)
                .stream().map(converter::convertForList).collect(Collectors.toList());
    }

    //forUsers and salesManager
    public ProductDto viewProduct(Long id) {
        return converter.convert(repository.findById(id).orElseThrow());
    }

    //for salesManager
    public List<ProductDtoList> getStoreProducts() {
        return repository.findAllByStoreId(1L)
                .stream().map(converter::convertForList).collect(Collectors.toList());
    }

    //for salesManager
    public ProductDto updateProduct(Long productId,ProductDto product) {
//        ProductModel fromDb = repository.findByStoreId(tokenResponse.getUserId());
        ProductModel fromDb = repository.findById(productId).orElseThrow();
        ProductDescription description = fromDb.getProductDescription();
        if (product.getName() != null
                && !product.getName().equals(fromDb.getName())) {
            fromDb.setName(product.getName());
        }
        if (product.getPrice() != null
                && !product.getPrice().equals(fromDb.getPrice())) {
            fromDb.setName(product.getName());
        }
        if (product.getProductDesc().getColor() != null
                && !product.getProductDesc().getColor().equals(description.getColor())) {
            description.setColor(product.getProductDesc().getColor());
        }
        if (product.getProductDesc().getMaterial() != null
                && !product.getProductDesc().getMaterial().equals(description.getMaterial())) {
            description.setMaterial(product.getProductDesc().getMaterial());
        }
        if (product.getProductDesc().getDescription() != null
                && !product.getProductDesc().getDescription().equals(description.getDescription())) {
            description.setDescription(product.getProductDesc().getDescription());
        }
        if (product.getProductDesc().getProductStock() != null
                && !product.getProductDesc().getProductStock().equals(description.getProductStock())) {
            description.setProductStock(product.getProductDesc().getProductStock());
        }
        return converter.convert(fromDb);
    }

    //forSales Manager
    public void setProductActiveStatus(Long id, Boolean status) {
        ProductModel product = repository.findById(id).orElseThrow();
        product.setActive(status);
        repository.save(product);
    }

    //forSales Manager
    public void createProduct(ProductDto productDto) {
        ProductModel product = converter.convertToModel(productDto);
        product.setCreateDate(LocalDate.now());
        product.setActive(false);
        var storeId = tokenResponse.getUserId();
        product.setStore(StoreModel.builder().id(storeId).build());
        repository.save(product);
    }

    //forSales Manager
    public void deleteProduct(Long id) {
        var user = StoreModel.builder().id(tokenResponse.getUserId()).build();
        repository.deleteProductEntityByStore(id, user);
    }
}
