package request;

import enums.ProductItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {
    @NonNull
    private ProductItem productItem;
    @NonNull
    private BigDecimal price;
    @NonNull
    private int quantity;
}
