package fr.epsi.mspr.repositories;

import fr.epsi.mspr.entities.Product;
import fr.epsi.mspr.entities.ProductInPurchase;
import fr.epsi.mspr.entities.ProductInPurchaseKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInPurchaseRepository extends JpaRepository <ProductInPurchase, ProductInPurchaseKey> {
    //get ProductInPurchase with biggest price
    List<ProductInPurchase> findByOrderByPriceDesc();
}
