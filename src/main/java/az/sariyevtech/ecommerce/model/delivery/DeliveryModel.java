package az.sariyevtech.ecommerce.model.delivery;

import az.sariyevtech.ecommerce.model.BaseEntity;
import az.sariyevtech.ecommerce.model.basket.BasketModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryModel extends BaseEntity {
    private String customerId;
    private String customerFullName;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    private City city;
    private String phoneNumber;
    private String zipCode;
    private String address;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "deliveryModel", orphanRemoval = true)
    private BasketModel basket;
    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
