package STP_KURS.GraphQL;

import STP_KURS.GraphQL.Entities.Cart;
import STP_KURS.GraphQL.Entities.Product;
import STP_KURS.GraphQL.Servecies.CartService;
import STP_KURS.GraphQL.Servecies.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class GraphQLController {

    private final ProductService productService;
    private final CartService cartService;

    public GraphQLController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @QueryMapping
    public List<Product> products() {
        return productService.getProducts();
    }

    @QueryMapping
    public Cart cart(Long id) {
        return cartService.getCart(id);
    }

    @MutationMapping
    public Cart addToCart(@Argument Long cartId, @Argument Long productId, @Argument Integer quantity) {
        return cartService.addToCart(cartId, productId, quantity);
    }
}