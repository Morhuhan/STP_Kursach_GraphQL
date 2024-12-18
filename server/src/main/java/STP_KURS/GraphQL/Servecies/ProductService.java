package STP_KURS.GraphQL.Servecies;

import STP_KURS.GraphQL.Entities.Product;
import STP_KURS.GraphQL.Entities.ProductPage;
import STP_KURS.GraphQL.Repos.ProductRepository;
import STP_KURS.GraphQL.Controllers.SubscriptionResolver;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SubscriptionResolver subscriptionResolver;

    public ProductService(ProductRepository productRepository, SubscriptionResolver subscriptionResolver) {
        this.productRepository = productRepository;
        this.subscriptionResolver = subscriptionResolver;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductPage getProductsByPage(String searchTerm, Double minPrice, Double maxPrice, Long categoryId, Integer page, Integer pageSize) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchTerm != null && !searchTerm.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + searchTerm + "%"));
            }
            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            if (categoryId != null) {
                predicates.add(criteriaBuilder.equal(root.get("category").get("id"), categoryId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        int currentPage = (page != null && page > 0) ? page - 1 : 0;
        int size = (pageSize != null && pageSize > 0) ? pageSize : 10;

        Pageable pageable = PageRequest.of(currentPage, size, Sort.by("id"));

        Page<Product> productPage = productRepository.findAll(spec, pageable);

        int totalPages = productPage.getTotalPages();

        return new ProductPage(
                productPage.getContent(),
                totalPages,
                currentPage + 1
        );
    }


    public Product updateProductStock(Long productId, Boolean inStock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setInStock(inStock);
        Product updatedProduct = productRepository.save(product);

        subscriptionResolver.publishProductUpdate(productId);

        return updatedProduct;
    }
}