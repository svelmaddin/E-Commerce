package az.sariyevtech.ecommerce.model.store;

import az.sariyevtech.ecommerce.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDetails extends BaseEntity {
    private String country;
    private String city;
    private String street;
    private String zipcode;
    private String address;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private StoreModel store;

}
