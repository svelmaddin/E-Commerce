package az.sariyevtech.ecommerce.controller;

import az.sariyevtech.ecommerce.dto.orderDto.OrderDto;
import az.sariyevtech.ecommerce.dto.request.OrderCreateRequest;
import az.sariyevtech.ecommerce.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //forAdmin
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    //forCustomers
    @GetMapping("/customerOrders")
    public ResponseEntity<List<OrderDto>> getCustomerOrders() {
        return ResponseEntity.ok(orderService.findUserOrders());
    }

    //forCustomers
    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderCreateRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @PostMapping("/edit/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long orderId, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, orderDto));
    }
}
