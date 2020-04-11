package request;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Slf4j
public class CalculateProductResponse {
    private List<CalculateProductModel> calculateProductModels = Lists.newArrayList();
    private BigDecimal subTotal = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal tax = BigDecimal.ZERO;

    public static CalculateProductResponseBuilder builder() {
        return new CustomCalculateProductResponseBuilder();
    }

    public static class CustomCalculateProductResponseBuilder extends CalculateProductResponseBuilder {
        @Override
        public CalculateProductResponse build() {
            CalculateProductModel reduce = super.calculateProductModels
                    .stream()
                    .reduce(new CalculateProductModel(), this::mergeCalculateProductModel);
            super.subTotal(reduce.getSubTotal());
            super.total(reduce.getTotal());
            super.tax(reduce.getTax().stripTrailingZeros());
            
            log.info("sum CalculateProductResponse subTotal:[{}], total:[{}], tax:[{}].",
                    reduce.getSubTotal(),
                    reduce.getTotal(),
                    reduce.getTax());
            return super.build();
        }

        private CalculateProductModel mergeCalculateProductModel(CalculateProductModel left, CalculateProductModel right) {
            CalculateProductModel calculateProductModel = new CalculateProductModel();
            calculateProductModel.setSubTotal(left.getSubTotal().add(right.getSubTotal()));
            calculateProductModel.setTotal(left.getTotal().add(right.getTotal()));
            calculateProductModel.setTax(left.getTax().add(right.getTax()));
            return calculateProductModel;
        }
    }

}
