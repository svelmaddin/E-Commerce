package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.StoreDto;
import az.sariyevtech.ecommerce.dto.converter.StoreConverter;
import az.sariyevtech.ecommerce.dto.request.CreateStoreRequest;
import az.sariyevtech.ecommerce.model.store.StoreModel;
import az.sariyevtech.ecommerce.repository.StoreRepository;
import az.sariyevtech.ecommerce.response.TokenResponse;
import az.sariyevtech.ecommerce.services.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final TokenResponse tokenResponse;
    private final StoreConverter storeConverter;

    public StoreServiceImpl(StoreRepository storeRepository,
                            TokenResponse tokenResponse,
                            StoreConverter storeConverter) {
        this.storeRepository = storeRepository;
        this.tokenResponse = tokenResponse;
        this.storeConverter = storeConverter;
    }

    @Override
    public StoreModel getCurrentUserStore() {
        return storeRepository.findByUserId(tokenResponse.getUserId());
    }

    @Override
    public void createStore(CreateStoreRequest request) {
        StoreModel store = storeConverter.convertCreateStoreToModel(request);
        store.setUserId(tokenResponse.getUserId());
        storeRepository.save(store);
    }

    @Override
    public List<StoreDto> getAllStore() {
        return null;
    }

    @Override
    public StoreDto getStoreById(Long storeId) {
        return null;
    }

    @Override
    public void deleteStore(Long id) {

    }

    @Override
    public StoreDto updateStore(Long id, CreateStoreRequest request) {
        return null;
    }

}
