package az.sariyevtech.ecommerce.model.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductReviewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private Boolean oneStar;
    @Column(nullable = true)
    private Boolean twoStar;
    @Column(nullable = true)
    private Boolean threeStar;
    @Column(nullable = true)
    private Boolean fourStar;
    @Column(nullable = true)
    private Boolean fiveStar;
    private String reviewText;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductModel product;

}
