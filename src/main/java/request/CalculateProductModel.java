package request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CalculateProductModel {
    private Product product;
    private BigDecimal salesTaxRate = BigDecimal.ZERO;
    private BigDecimal subTotal = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal tax = BigDecimal.ZERO;
}
