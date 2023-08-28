package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.basket.BasketDto;
import az.sariyevtech.ecommerce.dto.email.OrderStatusEmail;
import az.sariyevtech.ecommerce.dto.response.TokenResponse;
import az.sariyevtech.ecommerce.model.order.MakeOrder;
import az.sariyevtech.ecommerce.model.order.OrderStatus;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmailService {
    private final BasketServiceImpl basketService;
    private final MakeOrderServiceImpl makeOrderService;
    private final DeliveryServiceImpl deliveryService;
    private final TotalPriceServiceImpl totalPriceService;
    private final RestTemplate restTemplate;
    private final TokenResponse tokenResponse;

    public EmailService(BasketServiceImpl basketService,
                        MakeOrderServiceImpl makeOrderService,
                        DeliveryServiceImpl deliveryService,
                        TotalPriceServiceImpl totalPriceService,
                        RestTemplate restTemplate,
                        TokenResponse tokenResponse) {
        this.basketService = basketService;
        this.makeOrderService = makeOrderService;
        this.deliveryService = deliveryService;
        this.totalPriceService = totalPriceService;
        this.restTemplate = restTemplate;
        this.tokenResponse = tokenResponse;
    }

    public void sendOrderStatusEmailToCustomer() {
        BasketDto basket = basketService.getBasketDto();
        List<MakeOrder> orderDto = makeOrderService.getAllActiveOrders();
        List<String> productNames = orderDto.stream().map(MakeOrder::getName).toList();
        String orderProcessStatus = String.valueOf(OrderStatus.ORDER_PROCESSING);
        OrderStatusEmail orderStatusEmail = OrderStatusEmail.builder()
                .orderId(basket.getId())
                .productNames(productNames)
                .deliveryAddress("baku")
                .totalPrice(basket.getIntermediatePrice())
                .discount(basket.getDiscount())
                .orderStatus(orderProcessStatus)
                .customerId(tokenResponse.getUserId())
                .customerName("elmaddin")
                .build();
        final String URL = "http://localhost:8084/email/emailSendToCustomer";
        try {
            HttpEntity<OrderStatusEmail> entity = new HttpEntity<>(
                    new OrderStatusEmail(
                            orderStatusEmail.getOrderId(),
                            orderStatusEmail.getProductNames(),
                            orderStatusEmail.getDeliveryAddress(),
                            orderStatusEmail.getTotalPrice(),
                            orderStatusEmail.getDiscount(),
                            orderStatusEmail.getOrderStatus(),
                            orderStatusEmail.getCustomerId(),
                            orderStatusEmail.getCustomerName()
                    ));

            restTemplate.postForObject(URL, entity, OrderStatusEmail.class);
        } catch (Exception ignored) {
        }
    }
}
