package az.sariyevtech.ecommerce.dto.storeDto;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class StoreDetailsDto {
    private Long id;
    private String country;
    private String city;
    private String street;
    private String zipcode;
    private String address;
    private String phoneNumber;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Byte[] photo;
}
