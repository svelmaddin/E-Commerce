package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.exception.StoreNotFoundException;
import az.sariyevtech.ecommerce.response.TokenResponse;
import org.springframework.stereotype.Service;

import static az.sariyevtech.ecommerce.util.ErrorMessages.USER_STORE_NOT_FOUND;


@Service
public class ValidationService {
    private final StoreService storeService;

    public ValidationService(StoreService storeService) {
        this.storeService = storeService;
    }

    protected void checkUserStoreValid() {
        if (storeService.getCurrentUserStore() == null) {
            throw new StoreNotFoundException(USER_STORE_NOT_FOUND);
        }
    }
}
