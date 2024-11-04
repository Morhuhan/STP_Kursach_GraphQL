package STP_KURS.GraphQL.Repos;

import STP_KURS.GraphQL.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}