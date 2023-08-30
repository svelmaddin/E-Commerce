package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.model.order.MakeOrder;

import java.util.List;

public interface MakeOrderService {
    void makeOrder(Long productId, int count);

    List<MakeOrder> getAllActiveOrders();

}
