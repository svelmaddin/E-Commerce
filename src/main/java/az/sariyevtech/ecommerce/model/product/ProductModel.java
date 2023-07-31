package az.sariyevtech.ecommerce.model.product;

import az.sariyevtech.ecommerce.model.store.StoreModel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    @Enumerated(EnumType.STRING)
    private CategoryModel category;
    @CreationTimestamp
    private LocalDate createDate;
    private boolean active;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private StoreModel store;
    @OneToOne(cascade = CascadeType.ALL ,mappedBy = "product")
    private ProductDescription productDescription;
    @OneToMany(mappedBy = "product")
    private List<ProductReviewModel> productReview;

}
