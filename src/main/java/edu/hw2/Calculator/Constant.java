package edu.hw2.Calculator;

public record Constant(double value) implements Expr {
    @Override
    public double evaluate() {
        return value;
    }
}
