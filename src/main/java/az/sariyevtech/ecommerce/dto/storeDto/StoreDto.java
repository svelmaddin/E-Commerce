package az.sariyevtech.ecommerce.dto.storeDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StoreDto {
    public Long id;
    public String storeName;
    private StoreDetailsDto detailsDto;
}
