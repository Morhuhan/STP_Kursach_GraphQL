package STP_KURS.GraphQL.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> items;
}