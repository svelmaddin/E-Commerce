package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.productDto.ProductDto;
import az.sariyevtech.ecommerce.dto.response.TokenResponse;
import az.sariyevtech.ecommerce.model.basket.BasketModel;
import az.sariyevtech.ecommerce.model.basket.TotalPrice;
import az.sariyevtech.ecommerce.model.order.MakeOrder;
import az.sariyevtech.ecommerce.repository.BasketRepository;
import az.sariyevtech.ecommerce.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class BasketServiceImpl {
    private final BasketRepository basketRepository;
    private final ProductService productService;
    private final TokenResponse tokenResponse;
    private final DeliveryServiceImpl deliveryService;
    private final MakeOrderServiceImpl orderService;
    private final TotalPriceServiceImpl totalPriceService;

    public BasketServiceImpl(BasketRepository basketRepository,
                             ProductService productService,
                             TokenResponse tokenResponse,
                             MakeOrderServiceImpl orderService,
                             DeliveryServiceImpl deliveryService, TotalPriceServiceImpl totalPriceService) {
        this.basketRepository = basketRepository;
        this.productService = productService;
        this.tokenResponse = tokenResponse;
        this.orderService = orderService;
        this.deliveryService = deliveryService;
        this.totalPriceService = totalPriceService;
    }


    public void addProductToBasket(boolean giftPackage, Long deliveryId) {
        List<ProductDto> product = new ArrayList<>();
        List<MakeOrder> orders = orderService.getAllActiveOrders();
        double totalOrderPrice = orders.stream()
                .mapToDouble(MakeOrder::getTotalPrice)
                .sum();
        double totalDiscount = orders.stream()
                .mapToDouble(MakeOrder::getTotalDiscount)
                .sum();
        TotalPrice totalPrice = TotalPrice.builder()
                .intermediatePrice(totalOrderPrice)
                .discount(totalDiscount)
                .giftPackage(giftPackage)
                .build();
        BasketModel basket = BasketModel.builder()
                .customerId(tokenResponse.getUserId())
                .orders(new HashSet<>(orders))
                .deliveryModel(deliveryService.getById(deliveryId))
                .totalPrice(totalPrice)
                .build();
        basketRepository.save(basket);
        totalPriceService.createTotalPrice(totalPrice, basket);
    }
}
