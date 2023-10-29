package edu.hw2;

import static org.assertj.core.api.Assertions.assertThat;
import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle(0, 0)),
            Arguments.of(new Square(0))
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Test LSP")
    public void rectangleArea(Rectangle rect) {
        Rectangle rectangle = rect.setWidth(20);
        Rectangle actual = rectangle.setHeight(10);
        assertThat(actual.area()).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Test transformation square to rectangle")
    public void transformationTest() {
        Rectangle sq = new Square(5);
        assertThat(sq.setWidth(10).area()).isEqualTo(50d);
    }

    @Test
    @DisplayName("Test square")
    public void squareAreaTest() {
        Rectangle sq = new Square(5).setSide(10);
        assertThat(sq.area()).isEqualTo(100d);
    }
}
