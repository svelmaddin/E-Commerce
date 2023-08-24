package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.model.basket.BasketModel;
import az.sariyevtech.ecommerce.model.basket.TotalPrice;
import az.sariyevtech.ecommerce.repository.TotalPriceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TotalPriceServiceImpl {
    private final TotalPriceRepository totalPriceRepository;

    public TotalPriceServiceImpl(TotalPriceRepository totalPriceRepository) {
        this.totalPriceRepository = totalPriceRepository;
    }

    public void createTotalPrice(TotalPrice price, BasketModel basket) {
        TotalPrice totalPrice = totalPriceRepository.findByBasketId(basket.getId()).orElse(
                new TotalPrice()
        );
        totalPrice.setIntermediatePrice(price.getIntermediatePrice());
        totalPrice.setGiftPackage(price.isGiftPackage());
        totalPrice.setDiscount(price.getDiscount());
        totalPrice.setBasket(basket);
        totalPriceRepository.save(totalPrice);
    }
}
