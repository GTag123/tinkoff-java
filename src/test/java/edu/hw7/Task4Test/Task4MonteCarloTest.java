package edu.hw7.Task4Test;

import edu.hw7.Task4MonteCarlo.MonteCarlo;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class Task4MonteCarloTest {
    private Task4MonteCarloTest() {}

    @Test
    @DisplayName("Test getPI function")
    public void testGetPI() {
        double temp = new MonteCarlo().getPI(10_000_000L);
        assertThat(temp).isCloseTo(Math.PI, Offset.offset(0.01));

    }
}
