package az.sariyevtech.ecommerce.controller;

import az.sariyevtech.ecommerce.dto.request.StoreCreateRequest;
import az.sariyevtech.ecommerce.services.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/createStore")
    public ResponseEntity<Void> createStoreDetails(@RequestBody StoreCreateRequest request) {
        storeService.createStore(request);
        return ResponseEntity.ok().build();
    }

}
