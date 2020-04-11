package service.impl;

import enums.LocationType;
import enums.ProductType;
import service.CalculateService;

import java.math.BigDecimal;

public class CaCalculateServiceImpl extends CalculateService {


    @Override
    public LocationType getLocationType() {
        return LocationType.CA;
    }

    /**
     * In California (CA), sales tax rate is 9.75%, food is exempt.
     *
     * @param productType
     * @return sales tax rate
     */
    @Override
    public BigDecimal getSalesTaxRate(ProductType productType) {
        if (productType == ProductType.FOOD) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(9.75);
    }

}
