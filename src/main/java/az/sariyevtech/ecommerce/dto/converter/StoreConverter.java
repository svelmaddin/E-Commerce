package az.sariyevtech.ecommerce.dto.converter;

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

//    public StoreDto
}
