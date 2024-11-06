package STP_KURS.GraphQL.Servecies;

import STP_KURS.GraphQL.Entities.Product;
import STP_KURS.GraphQL.Entities.Review;
import STP_KURS.GraphQL.Entities.User;
import STP_KURS.GraphQL.Repos.ProductRepository;
import STP_KURS.GraphQL.Repos.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    public Review addReview(Long productId, User author, String content, Double rating) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Review review = new Review();
        review.setAuthor(author);
        review.setContent(content);
        review.setRating(rating);
        review.setProduct(product);

        return reviewRepository.save(review);
    }
}