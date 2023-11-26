package edu.hw7.Task4Test;

import edu.hw7.Task4MonteCarlo.ParallelMonteCarlo;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class Task4ParallelMonteCarlo {
    @Test
    @DisplayName("Test getPI function by many threads")
    public void testGetPIByThreads() {
        double temp = new ParallelMonteCarlo().getPI(1_000_000_000L);
        assertThat(temp).isCloseTo(Math.PI, Offset.offset(0.01));
    }
}
