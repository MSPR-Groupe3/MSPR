package fr.epsi.mspr.repositories;

import fr.epsi.mspr.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
