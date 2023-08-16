package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.storeDto.StoreDto;
import az.sariyevtech.ecommerce.dto.converter.StoreConverter;
import az.sariyevtech.ecommerce.dto.request.StoreCreateRequest;
import az.sariyevtech.ecommerce.model.store.StoreDetails;
import az.sariyevtech.ecommerce.model.store.StoreModel;
import az.sariyevtech.ecommerce.repository.StoreRepository;
import az.sariyevtech.ecommerce.response.TokenResponse;
import az.sariyevtech.ecommerce.services.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public void createStore(StoreCreateRequest request) {
        StoreModel store = storeConverter.toModel(request);
        StoreDetails details = storeConverter.toDetailsModel(request);
        store.setStoreDetails(details);
        store.setUserId(tokenResponse.getUserId());
        storeRepository.save(store);
    }

    @Override
    public List<StoreDto> getAllStore() {
        return storeRepository.findAll().stream()
                .map(storeConverter::convertFromModel).collect(Collectors.toList());
    }

    @Override
    public StoreDto getStoreById(Long storeId) {
        return (StoreDto) storeRepository.findById(storeId).stream()
                .map(storeConverter::convertFromModel);
    }

    @Override
    public void deleteStore(Long id) {

    }

    @Override
    public StoreDto updateStore(Long id, StoreCreateRequest request) {
        return null;
    }

}
