package az.sariyevtech.ecommerce.dto.converter;

import az.sariyevtech.ecommerce.dto.storeDto.StoreDetailsDto;
import az.sariyevtech.ecommerce.dto.storeDto.StoreDto;
import az.sariyevtech.ecommerce.dto.request.StoreCreateRequest;
import az.sariyevtech.ecommerce.model.store.StoreDetails;
import az.sariyevtech.ecommerce.model.store.StoreModel;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter {
    public StoreModel convertCreateStoreToModel(StoreCreateRequest request) {
        var storeDetails = StoreDetails.builder()
                .country(request.getCountry())
                .city(request.getCity())
                .street(request.getStreet())
                .zipcode(request.getZipcode())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
//                (LocalDate.now())
                .build();
        return StoreModel.builder()
                .storeName(request.getStoreName())
                .storeDetails(storeDetails)
                .build();
    }

    public StoreModel toModel(StoreCreateRequest request) {
        return StoreModel.builder()
                .storeName(request.getStoreName())
                .build();
    }

    public StoreDetails toDetailsModel(StoreCreateRequest request) {
        return StoreDetails.builder()
                .country(request.getCountry())
                .city(request.getCity())
                .street(request.getStreet())
                .zipcode(request.getZipcode())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
//                (LocalDate.now())
                .build();
    }

    public StoreDto convertFromModel(StoreModel storeModel) {
        var storeDetailsDto = StoreDetailsDto.builder()
                .id(storeModel.getStoreDetails().getId())
                .country(storeModel.getStoreDetails().getCountry())
                .city(storeModel.getStoreDetails().getCity())
                .street(storeModel.getStoreDetails().getStreet())
                .zipcode(storeModel.getStoreDetails().getZipcode())
                .address(storeModel.getStoreDetails().getAddress())
                .phoneNumber(storeModel.getStoreDetails().getPhoneNumber())
                .createTime(storeModel.getStoreDetails().getCreateAt())
                .updateTime(storeModel.getStoreDetails().getUpdateAt())
                .build();
        return StoreDto.builder()
                .id(storeModel.getId())
                .storeName(storeModel.getStoreName())
                .detailsDto(storeDetailsDto)
                .build();
    }

}
