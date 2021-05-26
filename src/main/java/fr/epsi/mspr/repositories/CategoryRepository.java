package fr.epsi.mspr.repositories;

import fr.epsi.mspr.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
