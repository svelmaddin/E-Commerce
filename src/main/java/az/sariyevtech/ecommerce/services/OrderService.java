package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.dto.orderDto.OrderDto;
import az.sariyevtech.ecommerce.dto.request.OrderCreateRequest;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();

    OrderDto findById(Long id);

    List<OrderDto> findUserOrders();

    OrderDto createOrder(OrderCreateRequest request);

    OrderDto updateOrder(Long id, OrderDto request);

    void deleteOrder(Long id);

}
