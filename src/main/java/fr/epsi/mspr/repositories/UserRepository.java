package fr.epsi.mspr.repositories;

import fr.epsi.mspr.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginEmail(String loginEmail);
}
