package STP_KURS.GraphQL.Repos;
import STP_KURS.GraphQL.Entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.user.username = :username")
    Optional<Cart> findByUser_Username(@Param("username") String username);
}