package edu.hw2.Calculator;

public record Negative(Expr expr) implements Expr {
    @Override
    public double evaluate() {
        return -expr.evaluate();
    }
}
