package service;

import enums.LocationType;
import enums.ProductType;
import lombok.extern.slf4j.Slf4j;
import request.CalculateProductModel;
import request.CalculateProductResponse;
import request.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public abstract class CalculateService {

    public abstract LocationType getLocationType();

    public abstract BigDecimal getSalesTaxRate(ProductType productType);


    public CalculateProductResponse calculate(List<Product> products) {
        List<CalculateProductModel> calculateProductModels = products.stream()
                .map(this::prepareCalculateProductModel)
                .map(this::calculate)
                .collect(Collectors.toList());

        return CalculateProductResponse.builder()
                .calculateProductModels(calculateProductModels)
                .build();
    }

    private CalculateProductModel prepareCalculateProductModel(Product product) {
        BigDecimal salesTaxRate = this.getSalesTaxRate(product.getProductItem().getProductType());
        CalculateProductModel calculateModel = new CalculateProductModel();
        calculateModel.setProduct(product);
        calculateModel.setSalesTaxRate(salesTaxRate);

        return calculateModel;
    }

    public CalculateProductModel calculate(CalculateProductModel calculateProductModel) {
        Product product = calculateProductModel.getProduct();
        BigDecimal subTotal = product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()));
        BigDecimal tax = calculateTaxAndRound(calculateProductModel.getSalesTaxRate(), subTotal);

        calculateProductModel.setTax(tax);
        calculateProductModel.setSubTotal(subTotal);
        calculateProductModel.setTotal(subTotal.add(tax));

        log.info(String.format("calculate result:[%s]", calculateProductModel));
        return calculateProductModel;
    }

    private BigDecimal calculateTaxAndRound(BigDecimal salesTaxRate, BigDecimal amount) {
        return taxRound(amount.multiply(salesTaxRate).divide(BigDecimal.valueOf(100)));
    }

    private BigDecimal taxRound(BigDecimal amount) {
        BigDecimal increment = BigDecimal.valueOf(0.05);
        return amount.divide(increment, 0, RoundingMode.UP).multiply(increment);
    }

}
