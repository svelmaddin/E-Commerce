package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.model.basket.BasketModel;
import az.sariyevtech.ecommerce.model.basket.TotalPrice;
import az.sariyevtech.ecommerce.repository.TotalPriceRepository;
import org.springframework.stereotype.Service;

@Service
public class TotalPriceServiceImpl {
    private final TotalPriceRepository totalPriceRepository;

    public TotalPriceServiceImpl(TotalPriceRepository totalPriceRepository) {
        this.totalPriceRepository = totalPriceRepository;
    }

    public void createTotalPrice(TotalPrice price, BasketModel basket) {
        TotalPrice t = TotalPrice.builder()
                .intermediatePrice(price.getIntermediatePrice())
                .giftPackage(price.isGiftPackage())
                .discount(price.getDiscount())
                .basket(basket)
                .build();
        totalPriceRepository.save(t);
    }
}
