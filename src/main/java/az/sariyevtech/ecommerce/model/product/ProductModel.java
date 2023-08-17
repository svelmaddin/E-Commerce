package az.sariyevtech.ecommerce.model.product;

import az.sariyevtech.ecommerce.model.BaseEntity;
import az.sariyevtech.ecommerce.model.store.StoreModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Entity
@Table(name = "products")
public class ProductModel extends BaseEntity {

    private String name;
    private double price;
    @Enumerated(EnumType.STRING)
    private CategoryModel category;
    private boolean active;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private StoreModel store;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "products", orphanRemoval = true)
    private ProductDescription productDescription;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductReviewModel> productReview;

    public ProductModel(Long id, LocalDateTime createAt, LocalDateTime updateAt,
                        String name, double price, CategoryModel category, boolean active,
                        StoreModel store, ProductDescription productDescription,
                        List<ProductReviewModel> productReview) {
        super(id, createAt, updateAt);
        this.name = name;
        this.price = price;
        this.category = category;
        this.active = active;
        this.store = store;
        this.productDescription = productDescription;
        this.productReview = productReview;
    }

    public ProductModel(String name, double price, CategoryModel category,
                        boolean active, StoreModel store, ProductDescription productDescription,
                        List<ProductReviewModel> productReview) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.active = active;
        this.store = store;
        this.productDescription = productDescription;
        this.productReview = productReview;
    }

    public ProductModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public StoreModel getStore() {
        return store;
    }

    public void setStore(StoreModel store) {
        this.store = store;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    public List<ProductReviewModel> getProductReview() {
        return productReview;
    }

    public void setProductReview(List<ProductReviewModel> productReview) {
        this.productReview = productReview;
    }
}
