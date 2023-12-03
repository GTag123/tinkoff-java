package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

public class Task1Test {
    private Task1Test() {}

    @ParameterizedTest
    @CsvSource(value = {
        "Hello world!, Svool dliow!",
        "Any fool can write code that a computer can understand." +
            " Good programmers write code that humans can understand. ― Martin Fowler" +
            ", Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw." +
            " Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
    })
    @DisplayName("Test atbash function default")
    void testAtbash(String string, String expected) {
        assertThat(Task1.atbash(string)).isEqualTo(expected);
    }

    @NullAndEmptySource
    @ParameterizedTest
    @DisplayName("Test null and empty atbush")
    public void message_shouldThrowException_whenMessageIsNullOrEmpty(String message) {
        assertThatThrownBy(() -> Task1.atbash(message)).isInstanceOf(IllegalArgumentException.class);
    }

}
