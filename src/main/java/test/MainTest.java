package test;

import com.google.common.collect.Lists;
import enums.LocationType;
import enums.ProductItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import dto.CalculateProductResponse;
import dto.Product;
import service.CalculateServiceFactory;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@Slf4j
public class MainTest {
    private static CalculateServiceFactory calculateServiceFactory = new CalculateServiceFactory();

    @Before
    public void before() throws Exception {
        log.info("start usecase-----------------------------------");
    }


    @Test
    public void testUseCase1() throws Exception {
        Product book = new Product(ProductItem.BOOK, BigDecimal.valueOf(17.99), 1);
        Product chips = new Product(ProductItem.POTATO_CHIPS, BigDecimal.valueOf(3.99), 1);
        CalculateProductResponse calculateProductResponse = calculateServiceFactory.get(LocationType.CA).calculate(Lists.newArrayList(book, chips));
        assertEquals(calculateProductResponse.getSubTotal(), BigDecimal.valueOf(21.98));
        assertEquals(calculateProductResponse.getTax(), BigDecimal.valueOf(1.80));
        assertEquals(calculateProductResponse.getTotal(), BigDecimal.valueOf(23.78));
    }

    @Test
    public void testUseCase2() throws Exception {
        Product book = new Product(ProductItem.BOOK, BigDecimal.valueOf(17.99), 1);
        Product pencil = new Product(ProductItem.PENCIL, BigDecimal.valueOf(2.99), 3);
        CalculateProductResponse calculateProductResponse = calculateServiceFactory.get(LocationType.NY).calculate(Lists.newArrayList(book, pencil));
        assertEquals(calculateProductResponse.getSubTotal(), BigDecimal.valueOf(26.96));
        assertEquals(calculateProductResponse.getTax(), BigDecimal.valueOf(2.40));
        assertEquals(calculateProductResponse.getTotal(), BigDecimal.valueOf(29.36));
    }

    @Test
    public void testUseCase3() throws Exception {
        Product pencil = new Product(ProductItem.PENCIL, BigDecimal.valueOf(2.99), 2);
        Product shirt = new Product(ProductItem.SHIRT, BigDecimal.valueOf(29.99), 1);
        CalculateProductResponse calculateProductResponse = calculateServiceFactory.get(LocationType.NY).calculate(Lists.newArrayList(pencil, shirt));
        assertEquals(calculateProductResponse.getSubTotal(), BigDecimal.valueOf(35.97));
        assertEquals(calculateProductResponse.getTax(), BigDecimal.valueOf(0.55));
        assertEquals(calculateProductResponse.getTotal(), BigDecimal.valueOf(36.52));
    }
}
