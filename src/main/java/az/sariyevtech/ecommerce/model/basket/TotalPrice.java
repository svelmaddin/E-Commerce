package az.sariyevtech.ecommerce.model.basket;

import az.sariyevtech.ecommerce.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TotalPrice extends BaseEntity {
    private Double intermediatePrice;
    private Double discount;
    private boolean giftPackage;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "totalPrice", orphanRemoval = true)
    private BasketModel basket;


    public Double getIntermediatePrice() {
        return intermediatePrice;
    }

    public void setIntermediatePrice(Double intermediatePrice) {
        this.intermediatePrice = intermediatePrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public boolean isGiftPackage() {
        return giftPackage;
    }

    public void setGiftPackage(boolean giftPackage) {
        this.giftPackage = giftPackage;
    }

    public BasketModel getBasket() {
        return basket;
    }

    public void setBasket(BasketModel basket) {
        this.basket = basket;
    }
}
