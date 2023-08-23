package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.orderDto.MakeOrderDto;
import az.sariyevtech.ecommerce.dto.productDto.ProductDto;
import az.sariyevtech.ecommerce.dto.response.TokenResponse;
import az.sariyevtech.ecommerce.model.order.MakeOrder;
import az.sariyevtech.ecommerce.repository.MakeOrderRepository;
import az.sariyevtech.ecommerce.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MakeOrderServiceImpl {
    private final TokenResponse tokenResponse;
    private final ProductService productService;
    private final MakeOrderRepository makeOrderRepository;

    public MakeOrderServiceImpl(TokenResponse tokenResponse, ProductService productService, MakeOrderRepository makeOrderRepository) {
        this.tokenResponse = tokenResponse;
        this.productService = productService;
        this.makeOrderRepository = makeOrderRepository;
    }

    public MakeOrderDto makeOrder(Long productId, int count) {
        ProductDto product = productService.viewProduct(productId);
        double totalPrice = product.getPrice() * count;
        double totalDiscount = product.getDiscount();
        if (product.getDiscount() != 0 && !product.getDiscount().equals(null)) {
            totalPrice = totalPrice - product.getDiscount();
            totalDiscount = product.getDiscount() * count;
        }
        MakeOrder orderDb = MakeOrder.builder()
                .productId(productId)
                .userId(tokenResponse.getUserId())
                .count(count)
                .totalDiscount(totalDiscount)
                .totalPrice(totalPrice)
                .active(true)
                .build();
        makeOrderRepository.save(orderDb);
        return convert(orderDb);
    }

    public List<MakeOrder> getAllActiveOrders() {
        return makeOrderRepository.findByActiveTrueAndUserId(tokenResponse.getUserId());
    }

    private MakeOrderDto convert(MakeOrder db) {
        return MakeOrderDto.builder()
                .id(db.getId())
                .productId(db.getProductId())
                .count(db.getCount())
                .totalPrice(db.getTotalPrice())
                .build();
    }

}

