package az.sariyevtech.ecommerce.model.basket;

import az.sariyevtech.ecommerce.model.BaseEntity;
import az.sariyevtech.ecommerce.model.delivery.DeliveryModel;
import az.sariyevtech.ecommerce.model.order.MakeOrder;
import az.sariyevtech.ecommerce.model.product.ProductModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "basket")
@Getter
@Setter
public class BasketModel extends BaseEntity {
    private String customerId;
//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.DETACH})
//    @JoinTable(
//            name = "orders_product",
//            joinColumns = @JoinColumn(name = "basket_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private Set<ProductModel> productList = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "totalPrice_id")
    private TotalPrice totalPrice;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private DeliveryModel deliveryModel;
    @OneToMany(mappedBy = "basket", fetch = FetchType.LAZY)
    private Set<MakeOrder> orders = new HashSet<>();
}
