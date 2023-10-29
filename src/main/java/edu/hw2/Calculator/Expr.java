package edu.hw2.Calculator;

public sealed interface Expr permits Constant, Negative, Exponent, Addition, Multiplication {
    double evaluate();
}
