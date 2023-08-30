package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.model.basket.BasketModel;
import az.sariyevtech.ecommerce.model.basket.TotalPrice;

public interface TotalPriceService {
    void createTotalPrice(TotalPrice price, BasketModel basket);
}
