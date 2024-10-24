package STP_KURS.GraphQL.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    private Long id;
    private String name;
    private Double price;
}