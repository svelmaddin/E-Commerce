package az.sariyevtech.ecommerce.model.store;

import az.sariyevtech.ecommerce.model.product.ProductModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 1L;
    private String name;

    @OneToMany(mappedBy = "store",fetch = FetchType.EAGER)
    private List<ProductModel> product = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private StoreDetails storeDetails;
    private Long userId;

}
