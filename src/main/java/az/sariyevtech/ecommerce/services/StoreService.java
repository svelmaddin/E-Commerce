package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.model.store.StoreModel;
import az.sariyevtech.ecommerce.repository.StoreRepository;
import az.sariyevtech.ecommerce.response.TokenResponse;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final TokenResponse tokenResponse;

    public StoreService(StoreRepository storeRepository, TokenResponse tokenResponse) {
        this.storeRepository = storeRepository;
        this.tokenResponse = tokenResponse;
    }


    protected StoreModel getCurrentUserStore() {
        return storeRepository.findByUserId(tokenResponse.getUserId());
    }
}
