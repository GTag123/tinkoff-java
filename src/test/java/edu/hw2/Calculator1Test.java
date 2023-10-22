package edu.hw2;

import edu.hw2.Calculator.Addition;
import edu.hw2.Calculator.Constant;
import edu.hw2.Calculator.Exponent;
import edu.hw2.Calculator.Multiplication;
import edu.hw2.Calculator.Negative;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Calculator1Test {
    @Test
    @DisplayName("Expressions test")
    public void calculatorTest(){
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negative(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));
        assertThat(res.evaluate()).isEqualTo(37d);
    }
}
