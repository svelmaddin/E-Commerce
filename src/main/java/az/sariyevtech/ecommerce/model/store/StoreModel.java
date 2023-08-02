package az.sariyevtech.ecommerce.model.store;

import az.sariyevtech.ecommerce.model.product.ProductModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
