package STP_KURS.GraphQL.Controllers;

import STP_KURS.GraphQL.Entities.Cart;
import STP_KURS.GraphQL.Entities.Product;
import STP_KURS.GraphQL.Entities.Review;
import STP_KURS.GraphQL.Servecies.CartService;
import STP_KURS.GraphQL.Servecies.ProductService;
import STP_KURS.GraphQL.Servecies.ReviewService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class GraphQLController {

    private final ProductService productService;
    private final CartService cartService;
    private final ReviewService reviewService;

    public GraphQLController(ProductService productService, CartService cartService, ReviewService reviewService) {
        this.productService = productService;
        this.cartService = cartService;
        this.reviewService = reviewService;
    }

    @QueryMapping
    public List<Product> products() {
        return productService.getProducts();
    }

    @QueryMapping
    public Product product(@Argument Long id) {
        return productService.getProductById(id);
    }

    @QueryMapping
    public Cart cart() {
        return cartService.getCartForCurrentUser();
    }

    @MutationMapping
    public Cart addToCart(@Argument("productId") Long productId, @Argument Integer quantity) {
        return cartService.addToCart(productId, quantity);
    }

    @MutationMapping
    public Review addReview(@Argument Long productId, @Argument Long authorId, @Argument String content, @Argument Double rating) {
        return reviewService.addReview(productId, authorId, content, rating);
    }
}