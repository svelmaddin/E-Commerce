package az.sariyevtech.ecommerce.controller;

import az.sariyevtech.ecommerce.dto.request.MakeOrder;
import az.sariyevtech.ecommerce.services.OrderService;
import az.sariyevtech.ecommerce.services.impl.BasketServiceImpl;
import az.sariyevtech.ecommerce.services.impl.MakeOrderServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final BasketServiceImpl basketService;
    private final MakeOrderServiceImpl makeOrderService;

    public OrderController(OrderService orderService, BasketServiceImpl basketService, MakeOrderServiceImpl makeOrderService) {
        this.orderService = orderService;
        this.basketService = basketService;
        this.makeOrderService = makeOrderService;
    }

    @PostMapping("/addOrder")
    public ResponseEntity<String> addOrder(@RequestBody MakeOrder order) {
        basketService.addProductToBasket(order.isGift(), order.getDeliveryId());
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/createPr")
    public ResponseEntity<String> makeOrder(@RequestParam(name = "id") Long id,
                                            @RequestParam(name = "count") int count) {
        makeOrderService.makeOrder(id, count);
        return ResponseEntity.ok("success");
    }

//    //forAdmin
//    @GetMapping
//    public ResponseEntity<List<OrderDto>> getAllOrders() {
//        return ResponseEntity.ok(orderService.getAllOrders());
//    }
//
//    //forCustomers
//    @GetMapping("/customerOrders")
//    public ResponseEntity<List<OrderDto>> getCustomerOrders() {
//        return ResponseEntity.ok(orderService.findUserOrders());
//    }
//
//    //forCustomers
//    @PostMapping("/create")
//    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderCreateRequest request) {
//        return ResponseEntity.ok(orderService.createOrder(request));
//    }
//
//    @PostMapping("/edit/{orderId}")
//    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long orderId, @RequestBody OrderDto orderDto) {
//        return ResponseEntity.ok(orderService.updateOrder(orderId, orderDto));
//    }
}
