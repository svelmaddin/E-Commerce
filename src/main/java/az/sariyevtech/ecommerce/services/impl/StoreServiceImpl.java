package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.storeDto.StoreDetailsDto;
import az.sariyevtech.ecommerce.dto.storeDto.StoreDto;
import az.sariyevtech.ecommerce.dto.converter.StoreConverter;
import az.sariyevtech.ecommerce.dto.request.StoreCreateRequest;
import az.sariyevtech.ecommerce.exception.StoreNotFoundException;
import az.sariyevtech.ecommerce.model.store.StoreDetails;
import az.sariyevtech.ecommerce.model.store.StoreModel;
import az.sariyevtech.ecommerce.repository.StoreRepository;
import az.sariyevtech.ecommerce.dto.response.TokenResponse;
import az.sariyevtech.ecommerce.services.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.sariyevtech.ecommerce.util.ErrorMessages.STORE_NOT_FOUND;

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
        return storeRepository.findByUserId(tokenResponse.getStoreId()).orElseThrow();
    }

    @Override
    public void createStore(StoreCreateRequest request) {
        StoreModel store = storeConverter.toModel(request);
        StoreDetails details = storeConverter.toDetailsModel(request);
        store.setStoreDetails(details);
        store.setUserId(request.getUserId());
        storeRepository.save(store);
    }

    @Override
    public List<StoreDto> getAllStore() {
        return storeRepository.findAll().stream()
                .map(storeConverter::convertFromModel).collect(Collectors.toList());
    }

    @Override
    public StoreDto getStoreById(Long storeId) {
        return storeConverter.convertFromModel(storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException(STORE_NOT_FOUND + storeId)));
    }

    @Override
    public StoreDto getStoreByUserId(String id) {
        return storeConverter.convertFromModel(storeRepository.findByUserId(id)
                .orElseThrow(() -> new StoreNotFoundException(STORE_NOT_FOUND + id)));
    }

    //TODO
    @Override
    public void deleteStore(Long id) {

    }

    //TODO
    @Override
    public StoreDto updateStore(Long id, StoreCreateRequest request) {
        return null;
    }

    @Override
    public void setStatus(String userId, boolean status) {
        StoreModel store = storeRepository.getStoreModelByUserId(userId)
                .orElseThrow(() -> new StoreNotFoundException(STORE_NOT_FOUND + userId));
        store.setActive(status);
        storeRepository.save(store);
    }

    @Override
    public StoreDetailsDto getStoreDetails(Long id) {
        StoreModel fromDb = storeRepository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException(STORE_NOT_FOUND + id));
        return StoreDetailsDto.builder()
                .id(fromDb.getStoreDetails().getId())
                .country(fromDb.getStoreDetails().getCountry())
                .city(fromDb.getStoreDetails().getCity())
                .street(fromDb.getStoreDetails().getStreet())
                .zipcode(fromDb.getStoreDetails().getZipcode())
                .address(fromDb.getStoreDetails().getAddress())
                .phoneNumber(fromDb.getStoreDetails().getPhoneNumber())
                .createTime(fromDb.getStoreDetails().getCreateAt())
                .updateTime(fromDb.getStoreDetails().getUpdateAt())
                .build();
    }

}
