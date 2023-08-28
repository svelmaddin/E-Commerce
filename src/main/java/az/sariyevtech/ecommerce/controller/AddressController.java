package az.sariyevtech.ecommerce.controller;

import az.sariyevtech.ecommerce.dto.delivery.DeliveryDto;
import az.sariyevtech.ecommerce.services.impl.DeliveryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final DeliveryServiceImpl deliveryService;

    public AddressController(DeliveryServiceImpl deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAddress(@RequestBody DeliveryDto dto) {
        deliveryService.addAddress(dto);
        return ResponseEntity.ok("Success");
    }
}
