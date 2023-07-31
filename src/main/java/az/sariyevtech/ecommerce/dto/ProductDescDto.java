package az.sariyevtech.ecommerce.dto;

import az.sariyevtech.ecommerce.model.product.SizeModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductDescDto {
    private Long id;
    private String color;
    private String material;
    private String description;
    private int productStock;
    @Enumerated(EnumType.STRING)
    private SizeModel productSize;
}
