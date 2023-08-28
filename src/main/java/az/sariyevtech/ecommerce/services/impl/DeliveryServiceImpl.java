package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.delivery.DeliveryDto;
import az.sariyevtech.ecommerce.dto.response.TokenResponse;
import az.sariyevtech.ecommerce.model.delivery.City;
import az.sariyevtech.ecommerce.model.delivery.DeliveryModel;
import az.sariyevtech.ecommerce.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl {
    private final TokenResponse tokenResponse;
    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(TokenResponse tokenResponse, DeliveryRepository deliveryRepository) {
        this.tokenResponse = tokenResponse;
        this.deliveryRepository = deliveryRepository;
    }

    public void addAddress(DeliveryDto dto) {
        DeliveryModel d = new DeliveryModel();
        d.setCustomerId(tokenResponse.getUserId());
        d.setCustomerFullName(dto.getCustomerFullName());
        d.setCity(City.valueOf(dto.getCity()));
        d.setZipCode(dto.getZipCode());
        d.setPhoneNumber(dto.getPhoneNumber());
        d.setAddress(dto.getAddress());
        deliveryRepository.save(d);
    }

    public DeliveryModel getById(Long id) {
        System.out.println("Delivery Id : " + id);
        return deliveryRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found with given id : " + id));}
}
