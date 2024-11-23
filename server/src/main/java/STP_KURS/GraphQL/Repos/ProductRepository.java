package STP_KURS.GraphQL.Repos;

import STP_KURS.GraphQL.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.reviews r LEFT JOIN FETCH r.author WHERE p.id = :id")
    Optional<Product> findByIdWithReviewsAndAuthor(@Param("id") Long id);

    Optional<Product> findByName(String name);
}