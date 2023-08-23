package az.sariyevtech.ecommerce.model.order;

import az.sariyevtech.ecommerce.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Entity
public class OrderModel extends BaseEntity {
    private String customerId;
    private Long productId;
    private int count;
    private String description;
    private String deliveryAddress;
    private String deliveryTime;
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Enumerated(EnumType.STRING)
    private DeliveryLoc deliveryLocType;

    public OrderModel(Long id, LocalDateTime createAt,
                      LocalDateTime updateAt, String customerId,
                      Long productId, int count, String description,
                      String deliveryAddress, String deliveryTime,
                      double totalPrice, OrderStatus orderStatus,
                      PaymentType paymentType, DeliveryLoc deliveryLocType) {
        super(id, createAt, updateAt);
        this.customerId = customerId;
        this.productId = productId;
        this.count = count;
        this.description = description;
        this.deliveryAddress = deliveryAddress;
        this.deliveryTime = deliveryTime;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
        this.deliveryLocType = deliveryLocType;
    }

    public OrderModel(String customerId, Long productId,
                      int count, String description, String deliveryAddress,
                      String deliveryTime, double totalPrice, OrderStatus orderStatus,
                      PaymentType paymentType, DeliveryLoc deliveryLocType) {
        this.customerId = customerId;
        this.productId = productId;
        this.count = count;
        this.description = description;
        this.deliveryAddress = deliveryAddress;
        this.deliveryTime = deliveryTime;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
        this.deliveryLocType = deliveryLocType;
    }

    public OrderModel() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public String getDescription() {
        return description;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public DeliveryLoc getDeliveryLocType() {
        return deliveryLocType;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public void setDeliveryLocType(DeliveryLoc deliveryLocType) {
        this.deliveryLocType = deliveryLocType;
    }
}
