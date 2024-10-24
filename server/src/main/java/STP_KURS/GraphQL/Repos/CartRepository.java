package STP_KURS.GraphQL.Repos;
import STP_KURS.GraphQL.Entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}