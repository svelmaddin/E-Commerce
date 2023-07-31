package az.sariyevtech.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StoreModelDto {
    private Long id;
    private String name;
}
