package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProductItem {
    BOOK(ProductType.OTHER),
    PENCIL(ProductType.OTHER),
    POTATO_CHIPS(ProductType.FOOD),
    SHIRT(ProductType.CLOTHING);

    @Getter
    private ProductType productType;

}
