package STP_KURS.GraphQL.Security;

import STP_KURS.GraphQL.Entities.Product;
import STP_KURS.GraphQL.Repos.ProductRepository;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Controller
public class SubscriptionResolver {

    private final Sinks.Many<Product> productSink = Sinks.many().multicast().onBackpressureBuffer();
    private final ProductRepository productRepository;

    public SubscriptionResolver(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @SubscriptionMapping
    public Flux<Product> productUpdated(@Argument Long id) {
        if (id != null) {
            return productSink.asFlux()
                    .filter(product -> product.getId().equals(id));
        } else {
            return productSink.asFlux();
        }
    }

    public void publishProductUpdate(Long productId) {
        Product updatedProduct = productRepository.findByIdWithReviewsAndAuthor(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Логируем данные
        System.out.println("Publishing product update: " + updatedProduct);

        productSink.tryEmitNext(updatedProduct);
    }
}