package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.request.OrderCreateRequest;
import az.sariyevtech.ecommerce.exception.OutOfStockException;
import az.sariyevtech.ecommerce.exception.StoreNotFoundException;
import az.sariyevtech.ecommerce.model.product.ProductModel;
import org.springframework.stereotype.Service;

import static az.sariyevtech.ecommerce.util.ErrorMessages.OUT_OF_STOCK;
import static az.sariyevtech.ecommerce.util.ErrorMessages.USER_STORE_NOT_FOUND;


@Service
public class ValidationService {
    private final StoreServiceImpl storeServiceImpl;
    private final ProductServiceImpl productService;

    public ValidationService(StoreServiceImpl storeServiceImpl, ProductServiceImpl productService) {
        this.storeServiceImpl = storeServiceImpl;
        this.productService = productService;
    }

    protected void checkUserStoreValid() {
        if (storeServiceImpl.getCurrentUserStore() == null) {
            throw new StoreNotFoundException(USER_STORE_NOT_FOUND);
        }
    }

    protected void checkStockAndRequestCount(OrderCreateRequest request, Long id) {
        ProductModel product = productService.getProductFromDb(id);
        if (product.getProductDescription().getProductStock() == null) {
            throw new OutOfStockException(OUT_OF_STOCK);
        }
        if (request.getCount() > product.getProductDescription().getProductStock()) {
            throw new OutOfStockException(OUT_OF_STOCK);
        }

    }
}
