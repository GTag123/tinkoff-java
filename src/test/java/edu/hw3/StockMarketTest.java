package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarketClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class StockMarketTest {
    private StockMarketTest() {}

    private static Arguments[] getArguments1() {
        return new Arguments[] {
            Arguments.of(new StockMarketClass("AFLT", 40.26)),
            Arguments.of(new StockMarketClass("GAZP", 167.26)),
        };
    }

    @ParameterizedTest
    @MethodSource("getArguments1")
    @DisplayName("Test add function")
    public void test1StockMarketClass(StockMarketClass stockMarketClass) {
        stockMarketClass.add(new Stock("MTSS", 282.15));
        stockMarketClass.add(new Stock("MAGN", 53.15));
        assertThat(stockMarketClass.mostValuableStock()).isEqualTo(new Stock("MTSS", 282.15));
    }

    private static Arguments[] getArguments2() {
        return new Arguments[] {
            Arguments.of(new StockMarketClass("LKOH", 7318.0)),
        };
    }

    @ParameterizedTest
    @MethodSource("getArguments2")
    @DisplayName("Test mostValuableStock function")
    public void test2StockMarketClass(StockMarketClass stockMarketClass) {
        stockMarketClass.add(new Stock("MTSS", 282.15));
        stockMarketClass.add(new Stock("OZONDR", 2680.00));
        assertThat(stockMarketClass.mostValuableStock()).isEqualTo(new Stock("LKOH", 7318.0));
    }

    @Test
    @DisplayName("Test remove function")
    public void test3StockMarketClass() {
        var stockMarketClass = new StockMarketClass("AFLT", 40.26);
        stockMarketClass.add(new Stock("MTSS", 282.15));
        stockMarketClass.add(new Stock("OZONDR", 2680.00));

        stockMarketClass.remove(new Stock("OZONDR", 2680.00));
        assertThat(stockMarketClass.mostValuableStock()).isEqualTo(new Stock("MTSS", 282.15));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null test for add and remove")
    public void testEmptyStockMarketClass(Stock testStock) {
        StockMarketClass stockMarket = new StockMarketClass();
        org.junit.jupiter.api.Assertions.assertAll(
            () -> assertThatThrownBy(() -> stockMarket.remove(testStock)).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> stockMarket.add(testStock)).isInstanceOf(IllegalArgumentException.class)
        );;
    }

}
