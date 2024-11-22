package STP_KURS.GraphQL.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private String imageUrl;
    private String description;
    private Double rating;

    @Column(name = "in_stock")
    private Boolean inStock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude  // Exclude reviews to prevent recursion
    private List<Review> reviews;

    @ManyToOne
    @ToString.Exclude  // Already excluded, but ensure it's there
    @JoinColumn(name = "category_id")
    private Category category;
}