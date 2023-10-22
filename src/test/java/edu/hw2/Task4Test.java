package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    private static CallingInfo sampleMethod() {
        return CallingInfo.callingInfo();
    }

    @Test
    @DisplayName("Test callingInfo")
    public void testCallingInfo() {
        assertThat(sampleMethod()).extracting("className", "methodName")
            .contains("edu.hw2.Task4Test", "sampleMethod");
    }
}
