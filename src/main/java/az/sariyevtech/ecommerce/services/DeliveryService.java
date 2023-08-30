package az.sariyevtech.ecommerce.services;

import az.sariyevtech.ecommerce.dto.delivery.DeliveryDto;
import az.sariyevtech.ecommerce.model.delivery.DeliveryModel;

public interface DeliveryService {
    void addAddress(DeliveryDto dto);

    DeliveryModel getById(Long id);
}
