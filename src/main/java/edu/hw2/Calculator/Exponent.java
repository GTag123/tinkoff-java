package edu.hw2.Calculator;

public record Exponent(Expr expr, double exponent) implements Expr {
    @Override
    public double evaluate() {
        return Math.pow(expr.evaluate(), exponent);
    }
}
