package az.sariyevtech.ecommerce.controller;

import az.sariyevtech.ecommerce.services.impl.MakeOrderServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final MakeOrderServiceImpl makeOrderService;

    public OrderController(MakeOrderServiceImpl makeOrderService) {
        this.makeOrderService = makeOrderService;
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
