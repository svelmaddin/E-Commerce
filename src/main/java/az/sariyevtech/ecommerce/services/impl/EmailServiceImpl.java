package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.basket.BasketDto;
import az.sariyevtech.ecommerce.dto.email.OrderStatusEmail;
import az.sariyevtech.ecommerce.model.order.MakeOrder;
import az.sariyevtech.ecommerce.model.order.OrderStatus;
import az.sariyevtech.ecommerce.services.BasketService;
import az.sariyevtech.ecommerce.services.EmailService;
import az.sariyevtech.ecommerce.services.MakeOrderService;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    private final BasketService basketService;
    private final MakeOrderService makeOrderService;
    private final UserService userService;
    private final RestTemplate restTemplate;

    public EmailServiceImpl(BasketService basketService,
                            MakeOrderService makeOrderService,
                            UserService userService, RestTemplate restTemplate
                            ) {
        this.basketService = basketService;
        this.makeOrderService = makeOrderService;
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @Override
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
                .customerId(userService.currentUser().getId())
                .customerName(userService.currentUser().getName())
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
