package fr.epsi.mspr.repositories;

import fr.epsi.mspr.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
