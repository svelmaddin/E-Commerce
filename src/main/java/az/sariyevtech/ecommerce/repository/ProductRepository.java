package az.sariyevtech.ecommerce.repository;

import az.sariyevtech.ecommerce.model.product.ProductModel;
import az.sariyevtech.ecommerce.model.store.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findAllByActive(boolean active);
    List<ProductModel> findAllByStoreId(Long storeId);
    ProductModel findByIdAndActive(Long id, boolean active);
    ProductModel findByStoreId(Long storeId);
    @Modifying
    @Query("DELETE from ProductModel c WHERE c.store=:user AND c.id=:productId")
    void deleteProductEntityByStore(Long productId, StoreModel user);
}
