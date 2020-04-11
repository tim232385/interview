package service.impl;

import enums.LocationType;
import enums.ProductType;
import service.CalculateService;

import java.math.BigDecimal;

public class NyCalculateServiceImpl extends CalculateService {


    @Override
    public LocationType getLocationType() {
        return LocationType.NY;
    }

    /**
     * In New York (NY), sales tax rate is 8.875%, food and clothing are exempt
     *
     * @param productType
     * @return sales tax rate
     */
    @Override
    public BigDecimal getSalesTaxRate(ProductType productType) {
        if (productType == ProductType.FOOD) {
            return BigDecimal.ZERO;
        } else if (productType == ProductType.CLOTHING) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(8.875);
    }

}
