package edu.hw2.Calculator;

public record Multiplication(Expr first, Expr second) implements Expr {
    @Override
    public double evaluate() {
        return first.evaluate() * second.evaluate();
    }
}
