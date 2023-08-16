package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.storeDto.StoreDto;
import az.sariyevtech.ecommerce.dto.converter.ProductConverter;
import az.sariyevtech.ecommerce.dto.productDto.ProductDto;
import az.sariyevtech.ecommerce.dto.productDto.ProductDtoList;
import az.sariyevtech.ecommerce.dto.request.ProductCreateRequest;
import az.sariyevtech.ecommerce.exception.ProductNotFoundException;
import az.sariyevtech.ecommerce.model.product.ProductDescription;
import az.sariyevtech.ecommerce.model.product.ProductModel;
import az.sariyevtech.ecommerce.repository.ProductRepository;
import az.sariyevtech.ecommerce.response.TokenResponse;
import az.sariyevtech.ecommerce.services.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static az.sariyevtech.ecommerce.util.ErrorMessages.*;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductConverter productConverter;
    private final StoreServiceImpl storeServiceImpl;
    private final TokenResponse tokenResponse;
    private final ValidationService validationService;


    public ProductServiceImpl(ProductRepository repository,
                              ProductConverter productConverter,
                              StoreServiceImpl storeServiceImpl,
                              TokenResponse tokenResponse,
                              ValidationService validationService) {
        this.repository = repository;
        this.productConverter = productConverter;
        this.storeServiceImpl = storeServiceImpl;
        this.tokenResponse = tokenResponse;
        this.validationService = validationService;
    }

    //forUsers and salesManager
    @Override
    public List<ProductDtoList> getAllProducts() {
        List<ProductModel> product = repository.findAllByActive(true)
                .orElseThrow(() -> new ProductNotFoundException(NOT_FOUND_ACTIVE_PRODUCTS));
        return product.stream()
                .map(productConverter::convertForList).collect(Collectors.toList());
    }

    //forUsers and salesManager
    @Override
    public ProductDto viewProduct(Long id) {
        ProductModel product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND + id));
        if (product.getProductDescription() != null) {
            return productConverter.convert(product);
        }
        return fromDbToDto(product);
    }

    //for salesManager
    @Override
    public List<ProductDtoList> getStoreProducts() {
        return repository.findByStoreUserId(tokenResponse.getUserId())
                .stream().map(productConverter::convertForList).collect(Collectors.toList());
    }

    //for salesManager
    @Override
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
        fromDb.setUpdateAt(LocalDateTime.now());
        return productConverter.convert(fromDb);
    }

    //forSales Manager
    @Override
    public void setProductActiveStatus(Long id, Boolean status) {
        ProductModel product = repository.findByIdAndStore(id, storeServiceImpl.getCurrentUserStore())
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND + id));
        product.setActive(status);
        product.setUpdateAt(LocalDateTime.now());
        repository.save(product);
    }

    //forSales Manager
    @Override
    public void createProduct(ProductCreateRequest request) {
        validationService.checkUserStoreValid();
        ProductModel product = productConverter.convertToModel(request);
        ProductDescription description = productConverter.productDescDtoConvertToModel(request.getProductDesc());
        product.setStore(storeServiceImpl.getCurrentUserStore());
        product.setProductDescription(description);
        description.setProducts(product);
        repository.save(product);
    }

    //forSales Manager
    @Override
    public void deleteProduct(Long id) {
        validationService.checkUserStoreValid();
        var user = storeServiceImpl.getCurrentUserStore();
        ProductModel product = repository.findByIdAndStore(id, user)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND + id));
        repository.delete(product);
    }

    private ProductDto fromDbToDto(ProductModel product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .createDate(product.getCreateAt())
                .store(StoreDto.builder().name(product.getStore().getName()).build())
                .productReview(productConverter.reviewListConvert(product.getProductReview()))
                .build();
    }

    protected ProductModel getProductFromDb(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND + id));
    }

}
