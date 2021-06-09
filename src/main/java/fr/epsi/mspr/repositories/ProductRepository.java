package fr.epsi.mspr.repositories;

import fr.epsi.mspr.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long category_id);
    List<Product> findByIsSellableTrue();

    //Update the quantity of product
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.quantityAvailable = p.quantityAvailable + ?2 WHERE p.id = ?1")
    public void updateQuantityById(Long id, int quantity);

}
