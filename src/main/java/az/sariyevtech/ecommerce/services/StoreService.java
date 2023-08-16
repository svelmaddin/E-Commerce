package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.dto.storeDto.StoreDto;
import az.sariyevtech.ecommerce.dto.request.StoreCreateRequest;
import az.sariyevtech.ecommerce.model.store.StoreModel;

import java.util.List;

public interface StoreService {
    StoreModel getCurrentUserStore();

    void createStore(StoreCreateRequest request);

    List<StoreDto> getAllStore();

    StoreDto getStoreById(Long storeId);

    void deleteStore(Long id);

    StoreDto updateStore(Long id, StoreCreateRequest request);

    void setStatus(String userId, boolean status);

    StoreDto getStoreByUserId(String Id);
}
