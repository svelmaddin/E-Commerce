package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.orderDto.OrderDto;
import az.sariyevtech.ecommerce.dto.converter.OrderConverter;
import az.sariyevtech.ecommerce.dto.productDto.ProductDescDto;
import az.sariyevtech.ecommerce.dto.productDto.ProductDto;
import az.sariyevtech.ecommerce.dto.request.OrderCreateRequest;
import az.sariyevtech.ecommerce.dto.request.OrderStatusRequest;
import az.sariyevtech.ecommerce.exception.OrderCantBeUpdateException;
import az.sariyevtech.ecommerce.exception.OrderNotFoundException;
import az.sariyevtech.ecommerce.model.order.DeliveryLoc;
import az.sariyevtech.ecommerce.model.order.OrderModel;
import az.sariyevtech.ecommerce.model.order.OrderStatus;
import az.sariyevtech.ecommerce.repository.OrderRepository;
import az.sariyevtech.ecommerce.services.OrderService;
import az.sariyevtech.ecommerce.services.ProductService;
import az.sariyevtech.ecommerce.services.StoreService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static az.sariyevtech.ecommerce.util.ErrorMessages.ORDER_CANT_UPDATE;
import static az.sariyevtech.ecommerce.util.ErrorMessages.ORDER_NOT_FOUND;

@Service
public class OrderServiceImpl implements OrderService {
    private final RestTemplate restTemplate;
    private final OrderRepository repository;
    private final OrderConverter converter;
    private final ProductService productService;
    private final UserService userService;
    private final StoreService storeService;

    public OrderServiceImpl(RestTemplate restTemplate,
                            OrderRepository repository,
                            OrderConverter converter,
                            ProductService productService,
                            UserService userService, StoreService storeService) {
        this.restTemplate = restTemplate;
        this.repository = repository;
        this.converter = converter;
        this.productService = productService;
        this.userService = userService;
        this.storeService = storeService;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return repository.findAll().stream()
                .map(converter::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(Long id) {
        final OrderModel order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND + id));
        return converter.toDto(order);
    }

    @Override
    public List<OrderDto> findUserOrders() {
        var userId = userService.currentUser().getId();
        return repository.findByCustomerId(userId).stream()
                .map(converter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderCreateRequest request) {
        final ProductDto product = productService.viewProduct(request.getProductId());
        final var customerId = userService.currentUser().getId();
        final OrderModel order = OrderModel.builder()
                .customerId(customerId)
                .productId(request.getProductId())
                .count(request.getCount())
                .description(request.getDescription())
                .deliveryAddress(request.getDeliveryAddress())
                .deliveryTime(request.getDeliveryTime())
                .paymentType(request.getPaymentType())
                .deliveryLocType(request.getDeliveryLocType())
                .build();
        order.setOrderStatus(OrderStatus.ORDER_PROCESSING);
        order.setTotalPrice(calculateTotalPrice(product.getId(),
                request.getCount(),
                request.getDeliveryLocType()));
        final OrderModel orderFromDb = repository.save(order);
        repository.save(order);
        sendOrderStatusEmailToCustomer(orderFromDb.getId());
        return converter.toDto(orderFromDb);
    }


    public Double calculateTotalPrice(Long productId, int count, DeliveryLoc loc) {
        final ProductDto product = productService.viewProduct(productId);
        double productTotalPriceWithDelivery = 0;
        double productTotalPrice = product.getPrice() * count;

        if (loc.equals(DeliveryLoc.METRO_STATION)) {
            productTotalPriceWithDelivery = productTotalPrice + 2;

        } else if (loc.equals(DeliveryLoc.CITY)) {
            productTotalPriceWithDelivery = productTotalPrice + 3;

        } else if (loc.equals(DeliveryLoc.DISTRICT)) {
            productTotalPriceWithDelivery = productTotalPrice + 5;

        } else if (loc.equals(DeliveryLoc.VILLAGE)) {
            productTotalPriceWithDelivery = productTotalPrice + 7;
        }
        return productTotalPriceWithDelivery;
    }

    @Transactional
    @Override
    public OrderDto updateOrder(Long id, OrderDto request) {
        OrderModel order = repository.findByIdAndCustomerId(id, userService.currentUser().getId())
                .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND + id));

        if (order.getOrderStatus().equals(OrderStatus.ORDER_PROCESSING)
                || order.getOrderStatus().equals(OrderStatus.ORDER_PAYMENT_DUE)) {

            if (request.getCount() != order.getCount()
                    && request.getCount() != 0) {
                order.setCount(request.getCount());
            }
            if (!request.getDeliveryAddress().equals(order.getDeliveryAddress())
                    && !request.getDeliveryAddress().isEmpty()) {
                order.setDeliveryAddress(request.getDeliveryAddress());
            }
            if (!request.getDescription().equals(order.getDescription())
                    && !request.getDescription().isEmpty()) {
                order.setDescription(request.getDescription());
            }
            if (!request.getDeliveryTime().equals(order.getDeliveryTime())
                    && !request.getDeliveryTime().isEmpty()) {
                order.setDeliveryTime(request.getDeliveryTime());
            }
            if (!request.getDeliveryLocType().equals(order.getDeliveryLocType())
                    && request.getDeliveryLocType() != null) {
                order.setDeliveryLocType(request.getDeliveryLocType());
            }
            assert request.getDeliveryLocType() != null;
            if (request.getCount() != 0) {
                var totalPrice = calculateTotalPrice(request.getProductId(),
                        request.getCount(),
                        request.getDeliveryLocType());
                order.setTotalPrice(totalPrice);
            }

            return converter.toDto(repository.save(order));
        } else {
            throw new OrderCantBeUpdateException(ORDER_CANT_UPDATE + order.getOrderStatus());
        }
    }

    @Override
    public ProductDto makeSingleOrder(Long productId, int count) {
        ProductDto formDb = productService.viewProduct(productId);
        ProductDto product = ProductDto.builder()
                .name(formDb.getName())
                .price(formDb.getPrice())
                .productDesc(ProductDescDto.builder()
                        .productStock(formDb.getProductDesc().getProductStock())
                        .build())
                .build();
        if (count > product.getProductDesc().getProductStock()) {
            throw new RuntimeException();
        } else {
            return product;
        }
    }

    private void sendOrderStatusEmailToCustomer(Long orderId) {
        OrderModel order = repository.findById(orderId).orElseThrow();
        var product = productService.viewProduct(order.getProductId());
        var storeUserId = storeService.getUserIdByStoreId(product.getStore().getId());
        String orderProcessStatus = String.valueOf(OrderStatus.ORDER_PROCESSING);
        OrderStatusRequest orderStatus = OrderStatusRequest.builder()
                .customerId(order.getCustomerId())
                .storeId(storeUserId)
                .orderId(order.getId())
                .details(order.getDescription())
                .price(order.getTotalPrice())
                .orderStatus(orderProcessStatus)
                .build();
        final String URL = "http://localhost:8084/email/emailSendToCustomer";
        try {
            HttpEntity<OrderStatusRequest> entity = new HttpEntity<>(
                    new OrderStatusRequest(orderStatus.getCustomerId(),
                            orderStatus.getStoreId(),
                            orderStatus.getOrderId(),
                            orderStatus.getDetails(),
                            orderStatus.getPrice(),
                            orderStatus.getOrderStatus())
            );

            restTemplate.postForObject(URL, entity, OrderStatusRequest.class);
        } catch (Exception ignored) {
        }
    }
}
