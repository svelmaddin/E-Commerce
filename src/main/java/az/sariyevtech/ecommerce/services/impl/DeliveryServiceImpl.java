package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.delivery.DeliveryDto;
import az.sariyevtech.ecommerce.model.delivery.City;
import az.sariyevtech.ecommerce.model.delivery.DeliveryModel;
import az.sariyevtech.ecommerce.repository.DeliveryRepository;
import az.sariyevtech.ecommerce.services.DeliveryService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final UserService userService;
    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(UserService userService, DeliveryRepository deliveryRepository) {
        this.userService = userService;
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public void addAddress(DeliveryDto dto) {
        DeliveryModel d = new DeliveryModel();
        d.setCustomerId(userService.currentUser().getId());
        d.setCustomerFullName(dto.getCustomerFullName());
        d.setCity(City.valueOf(dto.getCity()));
        d.setZipCode(dto.getZipCode());
        d.setPhoneNumber(dto.getPhoneNumber());
        d.setAddress(dto.getAddress());
        deliveryRepository.save(d);
    }

    @Override
    public DeliveryModel getById(Long id) {
        System.out.println("Delivery Id : " + id);
        return deliveryRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found with given id : " + id));
    }
}
