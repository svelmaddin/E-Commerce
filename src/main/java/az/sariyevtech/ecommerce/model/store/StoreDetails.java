package az.sariyevtech.ecommerce.model.store;

import az.sariyevtech.ecommerce.model.product.ProductReviewModel;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
public class StoreDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String city;
    private String street;
    private String zipcode;
    private String address;
    private String phoneNumber;
    @Lob
    @Column(length = 10485760)
    private Byte[] photo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private StoreModel store;

}
