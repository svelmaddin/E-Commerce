package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.dto.converter.ProductConverter;
import az.sariyevtech.ecommerce.dto.ProductDto;
import az.sariyevtech.ecommerce.dto.ProductDtoList;
import az.sariyevtech.ecommerce.dto.request.ProductCreateRequest;
import az.sariyevtech.ecommerce.exception.ProductNotFoundException;
import az.sariyevtech.ecommerce.model.product.ProductDescription;
import az.sariyevtech.ecommerce.model.product.ProductModel;
import az.sariyevtech.ecommerce.model.store.StoreModel;
import az.sariyevtech.ecommerce.repository.ProductRepository;
import az.sariyevtech.ecommerce.repository.StoreRepository;
import az.sariyevtech.ecommerce.response.TokenResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static az.sariyevtech.ecommerce.util.ErrorMessages.*;


@Service
public class ProductService {
    private final ProductRepository repository;
    private final StoreRepository storeRepository;
    private final ProductConverter converter;
    private final TokenResponse tokenResponse;

    public ProductService(ProductRepository repository,
                          StoreRepository storeRepository,
                          ProductConverter converter,
                          TokenResponse tokenResponse) {
        this.repository = repository;
        this.storeRepository = storeRepository;
        this.converter = converter;
        this.tokenResponse = tokenResponse;
    }

    //forUsers and salesManager
    public List<ProductDtoList> getAllProducts() {
        List<ProductModel> product = repository.findAllByActive(true)
                .orElseThrow(() -> new ProductNotFoundException(NOT_FOUND_ACTIVE_PRODUCTS));
        return product.stream()
                .map(converter::convertForList).collect(Collectors.toList());
    }

    //forUsers and salesManager
    public ProductDto viewProduct(Long id) {
        ProductModel product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND + id));
        if (!product.isActive()) {
            throw new ProductNotFoundException(NOT_FOUND_ACTIVE_PRODUCT + id);
        }
        return converter.convert(product);
    }

    //for salesManager
    public List<ProductDtoList> getStoreProducts() {
        return repository.findAllByStoreId(tokenResponse.getUserId())
                .stream().map(converter::convertForList).collect(Collectors.toList());
    }

    //for salesManager
    public ProductDto updateProduct(Long productId, ProductDto dto) {
        ProductModel fromDb = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND + productId));
        ProductDescription description = fromDb.getProductDescription();
        if (dto.getName() != null
                && !dto.getName().equals(fromDb.getName())) {
            fromDb.setName(dto.getName());
        }
        if (dto.getPrice() != null
                && !dto.getPrice().equals(fromDb.getPrice())) {
            fromDb.setPrice(dto.getPrice());
        }
        if (!dto.getProductDesc().getColor().equals(description.getColor())) {
            description.setColor(dto.getProductDesc().getColor());
        }
        if (dto.getProductDesc().getMaterial() != null
                && !dto.getProductDesc().getMaterial().equals(description.getMaterial())) {
            description.setMaterial(dto.getProductDesc().getMaterial());
        }
        if (dto.getProductDesc().getDescription() != null
                && !dto.getProductDesc().getDescription().equals(description.getDescription())) {
            description.setDescription(dto.getProductDesc().getDescription());
        }
        if (dto.getProductDesc().getProductStock() != null
                && !dto.getProductDesc().getProductStock().equals(description.getProductStock())) {
            description.setProductStock(dto.getProductDesc().getProductStock());
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
    public void setProductsMultipleActive(Set<Long> ids, Boolean status) {
        List<ProductModel> products = ids.stream()
                .map(id -> {
                    ProductModel product = repository.findById(id).orElse(null);
                    if (product != null) {
                        product.setActive(status);
                    }
                    return product;
                })
                .filter(Objects::nonNull)
                .toList();
    }

    //forSales Manager
    public void createProduct(ProductCreateRequest request) {
        StoreModel store = storeRepository.findByUserId(tokenResponse.getUserId());
        ProductModel product = converter.productCreateConvertToModel(request);
        product.setCreateDate(LocalDate.now());
        product.setActive(false);
        product.setStore(store);
        repository.save(product);
    }

    //forSales Manager
    public void deleteProduct(Long id) {
        var user = StoreModel.builder().id(tokenResponse.getUserId()).build();
        repository.deleteProductEntityByStore(id, user);
    }
}
