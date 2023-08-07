package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.dto.StoreDto;
import az.sariyevtech.ecommerce.dto.request.CreateStoreRequest;
import az.sariyevtech.ecommerce.model.store.StoreModel;

import java.util.List;

public interface StoreService {
    StoreModel getCurrentUserStore();
    void createStore(CreateStoreRequest request);
    List<StoreDto> getAllStore();
    StoreDto getStoreById(Long storeId);
    void deleteStore(Long id);
    StoreDto updateStore(Long id, CreateStoreRequest request);

}
