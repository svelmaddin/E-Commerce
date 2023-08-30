package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.dto.basket.BasketDto;
import az.sariyevtech.ecommerce.dto.request.CreateBasketRequest;
import az.sariyevtech.ecommerce.dto.response.BasketResponse;
import az.sariyevtech.ecommerce.model.basket.BasketModel;

public interface BasketService {
    void addProductToBasket(boolean giftPackage, Long deliveryId);

    void createBasket(CreateBasketRequest request);

    BasketModel findByUserId();

    BasketResponse getBasket();

    BasketDto getBasketDto();
}
