package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.basket.BasketDto;
import az.sariyevtech.ecommerce.dto.request.CreateBasketRequest;
import az.sariyevtech.ecommerce.dto.response.BasketResponse;
import az.sariyevtech.ecommerce.dto.response.OrderCheckOutResponse;
import az.sariyevtech.ecommerce.model.basket.BasketModel;
import az.sariyevtech.ecommerce.model.basket.TotalPrice;
import az.sariyevtech.ecommerce.model.order.MakeOrder;
import az.sariyevtech.ecommerce.repository.BasketRepository;
import az.sariyevtech.ecommerce.services.BasketService;
import az.sariyevtech.ecommerce.services.DeliveryService;
import az.sariyevtech.ecommerce.services.MakeOrderService;
import az.sariyevtech.ecommerce.services.TotalPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final TokenResponse tokenResponse;
    private final DeliveryService deliveryService;
    @Autowired
    private MakeOrderService orderService;
    private final TotalPriceService totalPriceService;

    public BasketServiceImpl(BasketRepository basketRepository,
                             TokenResponse tokenResponse,
                             DeliveryService deliveryService,
                             TotalPriceService totalPriceService) {
        this.basketRepository = basketRepository;
        this.tokenResponse = tokenResponse;
        this.deliveryService = deliveryService;
        this.totalPriceService = totalPriceService;
    }

    @Override
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

    @Override
    public void createBasket(CreateBasketRequest request) {
        BasketModel basket = BasketModel.builder()
                .customerId(request.getId())
                .build();
        basketRepository.save(basket);
    }

    @Override
    public BasketModel findByUserId() {
        return basketRepository.findByCustomerId(tokenResponse.getUserId());
    }

    @Override
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

    @Override
    public BasketDto getBasketDto() {
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
