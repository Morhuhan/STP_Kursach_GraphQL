package STP_KURS.GraphQL.Repos;

import STP_KURS.GraphQL.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}