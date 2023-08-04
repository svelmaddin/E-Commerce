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
    private Boolean oneStar;
    private Boolean twoStar;
    private Boolean threeStar;
    private Boolean fourStar;
    private Boolean fiveStar;
    private String reviewText;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductModel product;

}
