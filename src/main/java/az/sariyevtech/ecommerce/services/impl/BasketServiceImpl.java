package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.basket.BasketDto;
import az.sariyevtech.ecommerce.dto.request.CreateBasketRequest;
import az.sariyevtech.ecommerce.dto.response.BasketResponse;
import az.sariyevtech.ecommerce.dto.response.OrderCheckOutResponse;
import az.sariyevtech.ecommerce.dto.response.TokenResponse;
import az.sariyevtech.ecommerce.model.basket.BasketModel;
import az.sariyevtech.ecommerce.model.basket.TotalPrice;
import az.sariyevtech.ecommerce.model.order.MakeOrder;
import az.sariyevtech.ecommerce.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl {
    private final BasketRepository basketRepository;
    private final TokenResponse tokenResponse;
    private final DeliveryServiceImpl deliveryService;
    @Autowired
    private MakeOrderServiceImpl orderService;
    private final TotalPriceServiceImpl totalPriceService;

    public BasketServiceImpl(BasketRepository basketRepository,
                             TokenResponse tokenResponse,
                             DeliveryServiceImpl deliveryService,
                             TotalPriceServiceImpl totalPriceService) {
        this.basketRepository = basketRepository;
        this.tokenResponse = tokenResponse;
        this.deliveryService = deliveryService;
        this.totalPriceService = totalPriceService;
    }


    public void addProductToBasket(boolean giftPackage, Long deliveryId) {
        BasketModel fromDb = basketRepository.findByCustomerId(tokenResponse.getUserId());
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
        totalPriceService.createTotalPrice(totalPrice, fromDb);
        fromDb.setTotalPrice(totalPrice);
        fromDb.setOrders(new HashSet<>(orders));
        fromDb.setDeliveryModel(deliveryService.getById(deliveryId));
        basketRepository.save(fromDb);
    }

    public void createBasket(CreateBasketRequest request) {
        BasketModel basket = BasketModel.builder()
                .customerId(request.getId())
                .build();
        basketRepository.save(basket);
    }

    public BasketModel findByUserId() {
        return basketRepository.findByCustomerId(tokenResponse.getUserId());
    }

    public BasketResponse getBasket() {
        BasketModel basketModel = basketRepository.findByCustomerId(tokenResponse.getUserId());
        List<MakeOrder> orders = orderService.getAllActiveOrders();
        Set<OrderCheckOutResponse> outResponse = orders.stream().map(this::convert).collect(Collectors.toSet());
        return BasketResponse.builder()
                .id(basketModel.getId())
                .intermediatePrice(basketModel.getTotalPrice().getIntermediatePrice())
                .discount(basketModel.getTotalPrice().getDiscount())
                .orderDto(outResponse)
                .build();
    }

    protected BasketDto getBasketDto() {
        BasketModel basketModel = basketRepository.findByCustomerId(tokenResponse.getUserId());
        List<MakeOrder> orders = orderService.getAllActiveOrders();
        Set<OrderCheckOutResponse> outResponse = orders.stream().map(this::convert).collect(Collectors.toSet());
        return BasketDto.builder()
                .id(basketModel.getId())
                .intermediatePrice(basketModel.getTotalPrice().getIntermediatePrice())
                .discount(basketModel.getTotalPrice().getDiscount())
                .orderDto(outResponse)
                .build();
    }

    private OrderCheckOutResponse convert(MakeOrder fromDb) {
        return OrderCheckOutResponse.builder()
                .name(fromDb.getName())
                .productId(fromDb.getProductId())
                .count(fromDb.getCount())
                .price(fromDb.getTotalPrice())
                .build();
    }
}
