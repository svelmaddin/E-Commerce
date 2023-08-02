package az.sariyevtech.ecommerce.dto;

import az.sariyevtech.ecommerce.model.product.ProductDescription;
import az.sariyevtech.ecommerce.model.product.ProductModel;
import az.sariyevtech.ecommerce.model.product.ProductReviewModel;
import az.sariyevtech.ecommerce.model.store.StoreModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDtoConverter {
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

    //storeName , name ,details, price , star
    public ProductDtoList convertForList(ProductModel fromDb) {
        return ProductDtoList.builder()
                .id(fromDb.getId())
                .store(StoreModelDto.builder()
                        .name(fromDb.getStore().getName())
                        .build())
                .name(fromDb.getName())
                .productDesc(ProductDescDto.builder()
                        .description(fromDb.getProductDescription().getDescription())
                        .build())
                .price(fromDb.getPrice())
                .build();
    }

    private StoreModelDto storeConvert(StoreModel fromDb) {
        return StoreModelDto.builder()
                .id(fromDb.getId())
                .name(fromDb.getName())
                .build();
    }

    private StoreModel storeConvertToModel(StoreModelDto dto) {
        return StoreModel.builder()
                .name(dto.getName())
                .build();
    }

    private ProductDescDto productDescDtoConvert(ProductDescription from) {
        return new ProductDescDto(
                from.getId(),
                from.getColor(),
                from.getMaterial(),
                from.getDescription(),
                from.getProductStock(),
                from.getProductSize()
        );
    }

    private ProductDescription productDescDtoConvertToModel(ProductDescDto dto) {
        return ProductDescription.builder()
                .color(dto.getColor())
                .material(dto.getMaterial())
                .description(dto.getDescription())
                .productSize(dto.getProductSize())
                .build();
    }

    private ProductReviewModelDto reviewModelDtoConvert(ProductReviewModel from) {
        return new ProductReviewModelDto(
                from.getId(),
                from.getOneStar(),
                from.getTwoStar(),
                from.getThreeStar(),
                from.getFourStar(),
                from.getFiveStar(),
                from.getReviewText()
        );
    }


    private List<ProductReviewModelDto> reviewListConvert(List<ProductReviewModel> from) {
        return from.stream()
                .map(this::reviewModelDtoConvert)
                .collect(Collectors.toList());
    }

}
