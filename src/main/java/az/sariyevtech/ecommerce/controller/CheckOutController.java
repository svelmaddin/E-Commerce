package az.sariyevtech.ecommerce.controller;

import az.sariyevtech.ecommerce.dto.basket.BasketDto;
import az.sariyevtech.ecommerce.dto.request.CreateBasketRequest;
import az.sariyevtech.ecommerce.model.basket.BasketModel;
import az.sariyevtech.ecommerce.services.impl.BasketServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check-out")
public class CheckOutController {
    private final BasketServiceImpl basketService;

    public CheckOutController(BasketServiceImpl basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/")
    public ResponseEntity<BasketDto> getBasket(@RequestParam(name = "gift") boolean gift,
                                               @RequestParam(name = "deliveryId") Long id) {
        basketService.addProductToBasket(gift, id);
        return ResponseEntity.ok(basketService.getBasket());
    }

    @PostMapping("/createBasket")
    public ResponseEntity<Void> createBasket(@RequestBody CreateBasketRequest request) {
        basketService.createBasket(request);
        return ResponseEntity.ok().build();
    }
}
