package STP_KURS.GraphQL.Servecies;

import STP_KURS.GraphQL.Entities.Cart;
import STP_KURS.GraphQL.Entities.CartItem;
import STP_KURS.GraphQL.Entities.Product;
import STP_KURS.GraphQL.Entities.User;
import STP_KURS.GraphQL.Repos.CartRepository;
import STP_KURS.GraphQL.Repos.ProductRepository;
import STP_KURS.GraphQL.Repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Cart getCartForCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setItems(new ArrayList<>());
            cartRepository.save(cart);
            user.setCart(cart);
            userRepository.save(user);
        }
        return cart;
    }

    public Cart addToCart(Long productId, Integer quantity) {
        Cart cart = getCartForCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem item = new CartItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setCart(cart);

            cart.getItems().add(item);
        }

        return cartRepository.save(cart);
    }
}