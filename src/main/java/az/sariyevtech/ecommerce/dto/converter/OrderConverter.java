package az.sariyevtech.ecommerce.dto.converter;

import az.sariyevtech.ecommerce.dto.OrderDto;
import az.sariyevtech.ecommerce.model.order.OrderModel;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    //toModel
    //toDto

    public OrderDto toDto(OrderModel fromDb) {
        return OrderDto.builder()
                .orderId(fromDb.getId())
                .customerId(fromDb.getCustomerId())
                .productId(fromDb.getProductId())
                .count(fromDb.getCount())
                .totalPrice(fromDb.getTotalPrice())
                .description(fromDb.getDescription())
                .deliveryAddress(fromDb.getDeliveryAddress())
                .deliveryTime(fromDb.getDeliveryTime())
                .totalPrice(fromDb.getTotalPrice())
                .paymentType(fromDb.getPaymentType())
                .createAt(fromDb.getCreateAt())
                .deliveryLocType(fromDb.getDeliveryLocType())
                .build();
    }

    public OrderModel toModel(OrderDto fromDto) {
        return OrderModel.builder()
                .customerId(fromDto.getCustomerId())
                .productId(fromDto.getProductId())
                .count(fromDto.getCount())
                .description(fromDto.getDescription())
                .deliveryAddress(fromDto.getDeliveryAddress())
                .deliveryTime(fromDto.getDeliveryTime())
                .totalPrice(fromDto.getTotalPrice())
                .paymentType(fromDto.getPaymentType())
                .deliveryLocType(fromDto.getDeliveryLocType())
                .build();
    }
}
