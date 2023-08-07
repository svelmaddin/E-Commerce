package az.sariyevtech.ecommerce.dto;

import az.sariyevtech.ecommerce.model.store.StoreDetails;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StoreDto {
    public Long id;
    public String name;
    private StoreDetailsDto detailsDto;
}
