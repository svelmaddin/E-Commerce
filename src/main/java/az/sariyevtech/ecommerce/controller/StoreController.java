package az.sariyevtech.ecommerce.controller;

import az.sariyevtech.ecommerce.dto.request.StoreCreateRequest;
import az.sariyevtech.ecommerce.dto.storeDto.StoreDetailsDto;
import az.sariyevtech.ecommerce.dto.storeDto.StoreDto;
import az.sariyevtech.ecommerce.services.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id) {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @GetMapping("/byUserId/{id}")
    public ResponseEntity<StoreDto> getStoreByUserId(@PathVariable String id) {
        return ResponseEntity.ok(storeService.getStoreByUserId(id));
    }

    @PatchMapping("/edit")
    public ResponseEntity<Void> setStoreStatus(@RequestParam(name = "id") String id,
                                               @RequestParam(name = "status") boolean status) {
        storeService.setStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/details/byUserId/{id}")
    public ResponseEntity<StoreDetailsDto> getStoreDetailsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(storeService.getStoreDetails(id));
    }

}
