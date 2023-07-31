package az.sariyevtech.ecommerce.model.product;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ProductDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    private String material;
    private String description;
    private int productStock;
    @Enumerated(EnumType.STRING)
    private SizeModel productSize;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductModel product;


}
