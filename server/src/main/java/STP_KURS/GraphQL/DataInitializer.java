package STP_KURS.GraphQL;

import STP_KURS.GraphQL.Entities.*;
import STP_KURS.GraphQL.Repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer {

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initData(UserRepository userRepository,
                                      CategoryRepository categoryRepository,
                                      ProductRepository productRepository,
                                      CartRepository cartRepository,
                                      CartItemRepository cartItemRepository,
                                      ReviewRepository reviewRepository) {
        return args -> {
            // Add users
            if (userRepository.count() == 0) {
                User user1 = new User();
                user1.setUsername("user1");
                user1.setPassword(passwordEncoder.encode("password1"));

                User user2 = new User();
                user2.setUsername("user2");
                user2.setPassword(passwordEncoder.encode("password2"));

                userRepository.saveAll(List.of(user1, user2));
                System.out.println("Users created.");
            }

            // Add categories
            if (categoryRepository.count() == 0) {
                Category category1 = new Category();
                category1.setName("Electronics");

                Category category2 = new Category();
                category2.setName("Books");

                categoryRepository.saveAll(List.of(category1, category2));
                System.out.println("Categories created.");
            }

            // Add products
            if (productRepository.count() == 0) {
                Category electronics = categoryRepository.findByName("Electronics").orElseThrow();
                Category books = categoryRepository.findByName("Books").orElseThrow();

                Product product1 = new Product();
                product1.setName("Smartphone");
                product1.setPrice(699.99);
                product1.setDescription("Latest model smartphone with awesome features.");
                product1.setRating(4.5);
                product1.setCategory(electronics);
                product1.setInStock(true);

                Product product2 = new Product();
                product2.setName("Java Book");
                product2.setPrice(39.99);
                product2.setDescription("Comprehensive guide to Java programming.");
                product2.setRating(4.8);
                product2.setCategory(books);
                product2.setInStock(true);

                Product product3 = new Product();
                product3.setName("Laptop");
                product3.setPrice(999.99);
                product3.setDescription("High-performance laptop for work and gaming.");
                product3.setRating(4.7);
                product3.setCategory(electronics);
                product3.setInStock(true);

                Product product4 = new Product();
                product4.setName("Headphones");
                product4.setPrice(199.99);
                product4.setDescription("Noise-cancelling over-ear headphones.");
                product4.setRating(4.3);
                product4.setCategory(electronics);
                product4.setInStock(true);

                Product product5 = new Product();
                product5.setName("Camera");
                product5.setPrice(549.99);
                product5.setDescription("DSLR camera with 24.2 MP resolution.");
                product5.setRating(4.6);
                product5.setCategory(electronics);
                product5.setInStock(true);

                Product product6 = new Product();
                product6.setName("Wireless Mouse");
                product6.setPrice(29.99);
                product6.setDescription("Ergonomic wireless mouse with fast response.");
                product6.setRating(4.4);
                product6.setCategory(electronics);
                product6.setInStock(true);

                Product product7 = new Product();
                product7.setName("Keyboard");
                product7.setPrice(49.99);
                product7.setDescription("Mechanical keyboard with RGB lighting.");
                product7.setRating(4.8);
                product7.setCategory(electronics);
                product7.setInStock(true);

                Product product8 = new Product();
                product8.setName("Smart Watch");
                product8.setPrice(199.99);
                product8.setDescription("Smart watch with fitness tracking and notifications.");
                product8.setRating(4.2);
                product8.setCategory(electronics);
                product8.setInStock(true);

                Product product9 = new Product();
                product9.setName("Tablet");
                product9.setPrice(499.99);
                product9.setDescription("Lightweight tablet with a 10-inch display.");
                product9.setRating(4.5);
                product9.setCategory(electronics);
                product9.setInStock(true);

                Product product10 = new Product();
                product10.setName("Bluetooth Speaker");
                product10.setPrice(99.99);
                product10.setDescription("Portable Bluetooth speaker with deep bass.");
                product10.setRating(4.6);
                product10.setCategory(electronics);
                product10.setInStock(true);

                Product product11 = new Product();
                product11.setName("Python Programming Book");
                product11.setPrice(44.99);
                product11.setDescription("Learn Python programming with practical examples.");
                product11.setRating(4.7);
                product11.setCategory(books);
                product11.setInStock(true);

                Product product12 = new Product();
                product12.setName("Artificial Intelligence Book");
                product12.setPrice(59.99);
                product12.setDescription("Dive into the world of AI with this comprehensive guide.");
                product12.setRating(4.9);
                product12.setCategory(books);
                product12.setInStock(true);

                productRepository.saveAll(List.of(
                        product1, product2, product3, product4, product5,
                        product6, product7, product8, product9, product10,
                        product11, product12
                ));
                System.out.println("Products created.");
            }

            // Add carts
            if (cartRepository.count() == 0) {
                User user1 = userRepository.findByUsername("user1").orElseThrow();
                User user2 = userRepository.findByUsername("user2").orElseThrow();

                Cart cart1 = new Cart();
                cart1.setUser(user1);

                Cart cart2 = new Cart();
                cart2.setUser(user2);

                cartRepository.saveAll(List.of(cart1, cart2));
                System.out.println("Carts created.");
            }

            // Add cart items
            if (cartItemRepository.count() == 0) {
                Cart cart1 = cartRepository.findByUser_Username("user1").orElseThrow();
                Cart cart2 = cartRepository.findByUser_Username("user2").orElseThrow();

                Product smartphone = productRepository.findByName("Smartphone").orElseThrow();
                Product javaBook = productRepository.findByName("Java Book").orElseThrow();

                CartItem cartItem1 = new CartItem();
                cartItem1.setCart(cart1);
                cartItem1.setProduct(smartphone);
                cartItem1.setQuantity(1);

                CartItem cartItem2 = new CartItem();
                cartItem2.setCart(cart2);
                cartItem2.setProduct(javaBook);
                cartItem2.setQuantity(2);

                cartItemRepository.saveAll(List.of(cartItem1, cartItem2));
                System.out.println("Cart items created.");
            }

            // Add reviews
            if (reviewRepository.count() == 0) {
                User user1 = userRepository.findByUsername("user1").orElseThrow();
                User user2 = userRepository.findByUsername("user2").orElseThrow();

                Product smartphone = productRepository.findByName("Smartphone").orElseThrow();
                Product javaBook = productRepository.findByName("Java Book").orElseThrow();

                Review review1 = new Review();
                review1.setContent("Amazing smartphone, works perfectly!");
                review1.setRating(5.0);
                review1.setProduct(smartphone);
                review1.setAuthor(user1);

                Review review2 = new Review();
                review2.setContent("Very detailed and useful book.");
                review2.setRating(4.5);
                review2.setProduct(javaBook);
                review2.setAuthor(user2);

                reviewRepository.saveAll(List.of(review1, review2));
                System.out.println("Reviews created.");
            }
        };
    }
}
