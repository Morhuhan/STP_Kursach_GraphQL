package STP_KURS.GraphQL.Entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cartitem")
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;
}