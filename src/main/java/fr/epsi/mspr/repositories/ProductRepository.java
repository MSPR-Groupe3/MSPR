package fr.epsi.mspr.repositories;

import fr.epsi.mspr.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
