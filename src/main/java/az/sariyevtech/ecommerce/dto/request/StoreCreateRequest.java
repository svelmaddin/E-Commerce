package az.sariyevtech.ecommerce.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreCreateRequest {
    private String storeName;
    private String country;
    private String city;
    private String street;
    private String zipcode;
    private String address;
    private String phoneNumber;
}
