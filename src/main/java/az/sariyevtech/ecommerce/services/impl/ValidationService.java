package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.exception.StoreNotFoundException;
import org.springframework.stereotype.Service;

import static az.sariyevtech.ecommerce.util.ErrorMessages.USER_STORE_NOT_FOUND;


@Service
public class ValidationService {
    private final StoreServiceImpl storeServiceImpl;

    public ValidationService(StoreServiceImpl storeServiceImpl) {
        this.storeServiceImpl = storeServiceImpl;
    }

    protected void checkUserStoreValid() {
        if (storeServiceImpl.getCurrentUserStore() == null) {
            throw new StoreNotFoundException(USER_STORE_NOT_FOUND);
        }
    }
}
