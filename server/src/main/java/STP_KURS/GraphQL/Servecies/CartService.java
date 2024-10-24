package STP_KURS.GraphQL.Servecies;

import STP_KURS.GraphQL.Entities.Cart;
import STP_KURS.GraphQL.Entities.CartItem;
import STP_KURS.GraphQL.Repos.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElse(new Cart());
    }

    public Cart addToCart(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId).orElse(new Cart());
        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setQuantity(quantity);
        cart.getItems().add(item);
        return cartRepository.save(cart);
    }
}