package edu.project1;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {
    @Test
    @DisplayName("Test win with one mistake")
    public void testWin() {
        Session session = new Session("key", 2);
        session.guess('k');
        session.guess('e');
        session.guess('g');
        GuessResult actual = session.guess('y');

        assertThat(actual).isInstanceOf(GuessResult.Win.class);

    }

    @Test
    @DisplayName("Test lose because over maxAttempts")
    public void testLose() {
        Session session = new Session("my", 2);
        session.guess('t');
        session.guess('t');

        GuessResult actual = session.guess('t');
        assertThat(actual).isInstanceOf(GuessResult.Defeat.class);

    }

    @Test
    @DisplayName("Test correct hit state")
    public void sessionStateShouldChangingCorrectly_whenUserHasSuccessfulGuess() {
        Session session = new Session("key", 1);
        GuessResult successfulGuess = session.guess('k');
        assertThat(successfulGuess).isInstanceOf(GuessResult.SuccessfulGuess.class);
        assertThat(successfulGuess.state()).contains('k', '*', '*');
        assertThat(successfulGuess.attempt()).isEqualTo(0);
    }

    @Test
    @DisplayName("Test mistake state")
    public void sessionStateShouldChangingCorrectly_whenUserHasFailedGuess() {
        Session session = new Session("key", 2);
        GuessResult failedGuess = session.guess('m');
        assertThat(failedGuess.attempt()).isEqualTo(1);
        assertThat(failedGuess).isInstanceOf(GuessResult.FailedGuess.class);
        assertThat(failedGuess.state()).containsExactly('*', '*', '*');
    }

}
