package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.model.store.StoreModel;
import az.sariyevtech.ecommerce.repository.StoreRepository;
import az.sariyevtech.ecommerce.response.TokenResponse;
import az.sariyevtech.ecommerce.services.StoreService;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final TokenResponse tokenResponse;

    public StoreServiceImpl(StoreRepository storeRepository, TokenResponse tokenResponse) {
        this.storeRepository = storeRepository;
        this.tokenResponse = tokenResponse;
    }

    @Override
    public StoreModel getCurrentUserStore() {
        return storeRepository.findByUserId(tokenResponse.getUserId());
    }
}
