package STP_KURS.GraphQL.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductPage {
    private List<Product> products;
    private int totalPages;
    private int currentPage;
}