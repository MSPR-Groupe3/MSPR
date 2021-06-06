package fr.epsi.mspr.repositories;

import fr.epsi.mspr.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {


    @Query(value = "select p from Purchase p where p.contact.organization.id = ?1")
    public List<Purchase> findPurchaseByOrganization(Long id);


    @Query(value = "select p from Purchase p where p.contact.firstName like %:keyword% or p.contact.lastName like %:keyword% or p.reference like %:keyword%")
    public List<Purchase> findPurchaseByKeyword(@Param("keyword") String keyword);
}
