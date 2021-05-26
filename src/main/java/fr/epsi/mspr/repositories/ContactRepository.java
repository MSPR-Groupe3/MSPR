package fr.epsi.mspr.repositories;

import fr.epsi.mspr.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
