package az.sariyevtech.ecommerce.controller;

import az.sariyevtech.ecommerce.dto.request.CreateBasketRequest;
import az.sariyevtech.ecommerce.dto.response.BasketResponse;
import az.sariyevtech.ecommerce.services.impl.BasketServiceImpl;
import az.sariyevtech.ecommerce.services.impl.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check-out")
public class CheckOutController {
    private final BasketServiceImpl basketService;
    private final EmailService emailService;

    public CheckOutController(BasketServiceImpl basketService, EmailService emailService) {
        this.basketService = basketService;
        this.emailService = emailService;
    }



    @GetMapping("/")
    public ResponseEntity<BasketResponse> getBasket(@RequestParam(name = "gift") boolean gift,
                                                    @RequestParam(name = "deliveryId") Long id) {
        basketService.addProductToBasket(gift, id);
        emailService.sendOrderStatusEmailToCustomer();
        return ResponseEntity.ok(basketService.getBasket());
    }

    @PostMapping("/createBasket")
    public ResponseEntity<Void> createBasket(@RequestBody CreateBasketRequest request) {
        basketService.createBasket(request);
        return ResponseEntity.ok().build();
    }
}
