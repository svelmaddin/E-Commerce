package az.sariyevtech.ecommerce.controller;

import az.sariyevtech.ecommerce.dto.request.CreateBasketRequest;
import az.sariyevtech.ecommerce.dto.response.BasketResponse;
import az.sariyevtech.ecommerce.services.BasketService;
import az.sariyevtech.ecommerce.services.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check-out")
public class CheckOutController {
    private final BasketService basketService;
    private final EmailService emailServiceImpl;

    public CheckOutController(BasketService basketService, EmailService emailServiceImpl) {
        this.basketService = basketService;
        this.emailServiceImpl = emailServiceImpl;
    }


    @GetMapping("/")
    public ResponseEntity<BasketResponse> getBasket(@RequestParam(name = "gift") boolean gift,
                                                    @RequestParam(name = "deliveryId") Long id) {
        basketService.addProductToBasket(gift, id);
        emailServiceImpl.sendOrderStatusEmailToCustomer();
        return ResponseEntity.ok(basketService.getBasket());
    }

    @PostMapping("/createBasket")
    public ResponseEntity<Void> createBasket(@RequestBody CreateBasketRequest request) {
        basketService.createBasket(request);
        return ResponseEntity.ok().build();
    }
}
