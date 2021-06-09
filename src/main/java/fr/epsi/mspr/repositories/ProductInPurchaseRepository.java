package fr.epsi.mspr.repositories;
import fr.epsi.mspr.entities.Product;
import fr.epsi.mspr.entities.ProductInPurchase;
import fr.epsi.mspr.entities.ProductInPurchaseKey;
import fr.epsi.mspr.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductInPurchaseRepository extends JpaRepository<ProductInPurchase, ProductInPurchaseKey> {
    //get ProductInPurchase with biggest price
    List<ProductInPurchase> findByOrderByPriceDesc();

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query (
            value = "INSERT INTO product_in_purchase (purchase_id, product_id, quantity, price) VALUE (?1, ?2, ?3, ?4)",
            nativeQuery = true
    )
    public void insertProductInPurchase(Long purchaseId, Long productId, int quantity, float price);

    @Query(value = "select coalesce(sum (pip.quantity * pip.price), 0) from ProductInPurchase pip where pip.id.purchaseId = ?1")
    public float getTotalValue(long id);

}
