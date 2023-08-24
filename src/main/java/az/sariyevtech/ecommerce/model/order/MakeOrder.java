package az.sariyevtech.ecommerce.model.order;

import az.sariyevtech.ecommerce.model.BaseEntity;
import az.sariyevtech.ecommerce.model.basket.BasketModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class MakeOrder extends BaseEntity {
    private Long productId;
    private String userId;
    private String name;
    private int count;
    private Double totalPrice;
    private boolean active;
    private Double totalDiscount;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private BasketModel basket;
}
