package az.sariyevtech.ecommerce.dto.converter;

import az.sariyevtech.ecommerce.dto.*;
import az.sariyevtech.ecommerce.dto.productDto.ProductDescDto;
import az.sariyevtech.ecommerce.dto.productDto.ProductDto;
import az.sariyevtech.ecommerce.dto.productDto.ProductDtoList;
import az.sariyevtech.ecommerce.dto.productDto.ProductReviewDto;
import az.sariyevtech.ecommerce.dto.request.ProductCreateRequest;
import az.sariyevtech.ecommerce.model.product.ProductDescription;
import az.sariyevtech.ecommerce.model.product.ProductModel;
import az.sariyevtech.ecommerce.model.product.ProductReviewModel;
import az.sariyevtech.ecommerce.model.store.StoreModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {
    //toDto
    public ProductDto convert(ProductModel fromDb) {
        return ProductDto.builder()
                .id(fromDb.getId())
                .name(fromDb.getName())
                .price(fromDb.getPrice())
                .category(fromDb.getCategory())
                .createDate(fromDb.getCreateDate())
                .active(fromDb.isActive())
                .store(storeConvert(fromDb.getStore()))
                .productDesc(productDescDtoConvert(fromDb.getProductDescription()))
                .productReview(reviewListConvert(fromDb.getProductReview()))
                .build();
    }

    //toModel
    public ProductModel convertToModel(ProductCreateRequest dto) {
        return ProductModel.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .active(false)
                .createDate(LocalDate.now())
                .updateTime(LocalDate.now())
                .build();
    }

    //toDtoList
    public ProductDtoList convertForList(ProductModel fromDb) {
        return ProductDtoList.builder()
                .id(fromDb.getId())
                .name(fromDb.getName())
                .price(fromDb.getPrice())
                .active(fromDb.isActive())
                .store(StoreDto.builder()
                        .id(fromDb.getStore().getId())
                        .name(fromDb.getStore().getName())
                        .build())
                .build();
    }

    //toDto
    private StoreDto storeConvert(StoreModel fromDb) {
        return StoreDto.builder()
                .id(fromDb.getId())
                .name(fromDb.getName())
                .build();
    }

    //toDto
    public ProductDescDto productDescDtoConvert(ProductDescription from) {
        return ProductDescDto.builder()
                .color(from.getColor())
                .material(from.getMaterial())
                .description(from.getDescription())
                .productStock(from.getProductStock())
                .productSize(from.getProductSize())
                .build();
    }

    //toModel
    public ProductDescription productDescDtoConvertToModel(ProductDescDto dto) {
        return ProductDescription.builder()
                .color(dto.getColor())
                .material(dto.getMaterial())
                .description(dto.getDescription())
                .productSize(dto.getProductSize())
                .productStock(dto.getProductStock())
                .build();
    }

    //toDto
    protected ProductReviewDto reviewModelDtoConvert(ProductReviewModel from) {
        return ProductReviewDto.builder()
                .id(from.getId())
                .oneStar(from.getOneStar())
                .twoStar(from.getTwoStar())
                .threeStar(from.getThreeStar())
                .fourStar(from.getFourStar())
                .fiveStar(from.getFiveStar())
                .build();
    }

    //toDtoList
    public List<ProductReviewDto> reviewListConvert(List<ProductReviewModel> from) {
        return from.stream()
                .map(this::reviewModelDtoConvert)
                .collect(Collectors.toList());
    }

}
