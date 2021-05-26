package fr.epsi.mspr.repositories;

import fr.epsi.mspr.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
