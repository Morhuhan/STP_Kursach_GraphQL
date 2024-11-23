package STP_KURS.GraphQL.Repos;

import STP_KURS.GraphQL.Entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
