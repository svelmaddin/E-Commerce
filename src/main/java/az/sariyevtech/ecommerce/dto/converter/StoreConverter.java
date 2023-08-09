package az.sariyevtech.ecommerce.dto.converter;

import az.sariyevtech.ecommerce.dto.StoreDetailsDto;
import az.sariyevtech.ecommerce.dto.StoreDto;
import az.sariyevtech.ecommerce.dto.request.CreateStoreRequest;
import az.sariyevtech.ecommerce.model.store.StoreDetails;
import az.sariyevtech.ecommerce.model.store.StoreModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StoreConverter {
    public StoreModel convertCreateStoreToModel(CreateStoreRequest request) {
        var storeDetails = StoreDetails.builder()
                .country(request.getCountry())
                .city(request.getCity())
                .street(request.getStreet())
                .zipcode(request.getZipcode())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .createTime(LocalDate.now())
                .build();
        return StoreModel.builder()
                .name(request.getName())
                .storeDetails(storeDetails)
                .build();
    }

    public StoreDto convertFromModel(StoreModel storeModel) {
        var storeDetailsDto = StoreDetailsDto.builder()
                .country(storeModel.getStoreDetails().getCountry())
                .city(storeModel.getStoreDetails().getCity())
                .street(storeModel.getStoreDetails().getStreet())
                .zipcode(storeModel.getStoreDetails().getZipcode())
                .address(storeModel.getStoreDetails().getAddress())
                .phoneNumber(storeModel.getStoreDetails().getPhoneNumber())
                .createTime(storeModel.getStoreDetails().getCreateTime())
                .updateTime(storeModel.getStoreDetails().getUpdateTime())
                .build();
        return StoreDto.builder()
                .id(storeModel.getId())
                .name(storeModel.getName())
                .detailsDto(storeDetailsDto)
                .build();
    }

//        private Long id;
//    private String country;
//    private String city;
//    private String street;
//    private String zipcode;
//    private String address;
//    private String phoneNumber;
//    private LocalDate createTime;
//    private LocalDate updateTime;
//    private Byte[] photo;
}
