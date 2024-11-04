package STP_KURS.GraphQL.Controllers;

import STP_KURS.GraphQL.Entities.Cart;
import STP_KURS.GraphQL.Entities.Category;
import STP_KURS.GraphQL.Entities.Product;
import STP_KURS.GraphQL.Entities.Review;
import STP_KURS.GraphQL.Servecies.CartService;
import STP_KURS.GraphQL.Servecies.CategoryService;
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
    private final CategoryService categoryService;

    public GraphQLController(ProductService productService, CartService cartService, ReviewService reviewService, CategoryService categoryService) {
        this.productService = productService;
        this.cartService = cartService;
        this.reviewService = reviewService;
        this.categoryService = categoryService;
    }

    @QueryMapping
    public List<Product> products(
            @Argument String searchTerm,
            @Argument Double minPrice,
            @Argument Double maxPrice,
            @Argument Long categoryId
    ) {
        return productService.getFilteredProducts(searchTerm, minPrice, maxPrice, categoryId);
    }

    @QueryMapping
    public List<Category> categories() {
        return categoryService.getAllCategories();
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