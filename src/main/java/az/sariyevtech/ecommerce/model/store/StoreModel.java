package az.sariyevtech.ecommerce.model.store;

import az.sariyevtech.ecommerce.model.product.ProductModel;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class StoreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "store" ,cascade = CascadeType.ALL)
    private List<ProductModel> product =new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    private StoreDetails storeDetails;
}
