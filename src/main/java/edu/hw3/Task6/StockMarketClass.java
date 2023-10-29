package edu.hw3.Task6;

import java.util.PriorityQueue;
import java.util.Queue;

public class StockMarketClass implements StockMarket {

    private static final String ERROR_STR = "Empty object";
    private final Queue<Stock> stockList;

    public StockMarketClass() {
        stockList = new PriorityQueue<>(new StockComparator());
    }

    public StockMarketClass(String name, double prise) {
        stockList = new PriorityQueue<>(new StockComparator());
        this.add(new Stock(name, prise));
    }

    @Override
    public void add(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException(ERROR_STR);
        }
        stockList.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException(ERROR_STR);
        }
        stockList.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockList.peek();
    }
}
