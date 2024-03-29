package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.orderDto.MakeOrderDto;
import az.sariyevtech.ecommerce.dto.productDto.ProductDto;
import az.sariyevtech.ecommerce.model.order.MakeOrder;
import az.sariyevtech.ecommerce.repository.MakeOrderRepository;
import az.sariyevtech.ecommerce.services.BasketService;
import az.sariyevtech.ecommerce.services.MakeOrderService;
import az.sariyevtech.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakeOrderServiceImpl implements MakeOrderService {
    @Autowired
    private BasketService basketService;
    private final ProductService productService;
    private final MakeOrderRepository makeOrderRepository;
    private final UserService userService;

    public MakeOrderServiceImpl(
                                ProductService productService,
                                MakeOrderRepository makeOrderRepository,
                                UserService userService) {
        this.productService = productService;
        this.makeOrderRepository = makeOrderRepository;
        this.userService = userService;
    }

    @Override
    public void makeOrder(Long productId, int count) {
        ProductDto product = productService.viewProduct(productId);
        double totalPrice = product.getPrice() * count;
        double totalDiscount = product.getDiscount();
        if (product.getDiscount() != 0) {
            totalPrice = totalPrice - product.getDiscount();
            totalDiscount = product.getDiscount() * count;
        }
        MakeOrder orderDb = MakeOrder.builder()
                .productId(productId)
                .name(product.getName())
                .userId(userService.currentUser().getId())
                .count(count)
                .totalDiscount(totalDiscount)
                .totalPrice(totalPrice)
                .active(true)
                .build();
        orderDb.setBasket(basketService.findByUserId());
        makeOrderRepository.save(orderDb);
    }

    @Override
    public List<MakeOrder> getAllActiveOrders() {
        return makeOrderRepository.findByActiveTrueAndUserId("");
    }

    private MakeOrderDto convert(MakeOrder db) {
        return MakeOrderDto.builder()
                .id(db.getId())
                .productId(db.getProductId())
                .name(db.getName())
                .count(db.getCount())
                .totalPrice(db.getTotalPrice())
                .build();
    }

}

