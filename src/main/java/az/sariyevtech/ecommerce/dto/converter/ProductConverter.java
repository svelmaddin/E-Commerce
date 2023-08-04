package az.sariyevtech.ecommerce.dto.converter;

import az.sariyevtech.ecommerce.dto.*;
import az.sariyevtech.ecommerce.dto.request.ProductCreateRequest;
import az.sariyevtech.ecommerce.model.product.ProductDescription;
import az.sariyevtech.ecommerce.model.product.ProductModel;
import az.sariyevtech.ecommerce.model.product.ProductReviewModel;
import az.sariyevtech.ecommerce.model.store.StoreModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {
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

    public ProductModel convertToModel(ProductDto dto) {
        return ProductModel.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .createDate(dto.getCreateDate())
                .active(dto.isActive())
                .store(storeConvertToModel(dto.getStore()))
                .productDescription(productDescDtoConvertToModel(dto.getProductDesc()))
                .build();
    }

    public ProductDtoList convertForList(ProductModel fromDb) {
        return ProductDtoList.builder()
                .id(fromDb.getId())
                .store(StoreDto.builder()
                        .id(fromDb.getStore().getId())
                        .name(fromDb.getStore().getName())
                        .build())
                .name(fromDb.getName())
                .price(fromDb.getPrice())
                .build();
    }

    private StoreDto storeConvert(StoreModel fromDb) {
        return StoreDto.builder()
                .id(fromDb.getId())
                .name(fromDb.getName())
                .build();
    }

    private StoreModel storeConvertToModel(StoreDto dto) {
        return StoreModel.builder()
                .name(dto.getName())
                .build();
    }

    public ProductDescDto productDescDtoConvert(ProductDescription from) {
        return ProductDescDto.builder()
//                .id(from.getId())
                .color(from.getColor())
                .material(from.getMaterial())
                .description(from.getDescription())
                .productStock(from.getProductStock())
                .productSize(from.getProductSize())
                .build();
    }

    public ProductDescription productDescDtoConvertToModel(ProductDescDto dto) {
        return ProductDescription.builder()
                .color(dto.getColor())
                .material(dto.getMaterial())
                .description(dto.getDescription())
                .productSize(dto.getProductSize())
                .productStock(dto.getProductStock())
                .build();
    }

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


    private List<ProductReviewDto> reviewListConvert(List<ProductReviewModel> from) {
        return from.stream()
                .map(this::reviewModelDtoConvert)
                .collect(Collectors.toList());
    }

    public ProductModel productCreateConvertToModel(ProductCreateRequest request) {
        return ProductModel.builder()
                .name(request.getName())
                .price(request.getPrice())
                .category(request.getCategory())
                .productDescription(productDescDtoConvertToModel(request.getProductDesc()))
                .build();
    }

}
