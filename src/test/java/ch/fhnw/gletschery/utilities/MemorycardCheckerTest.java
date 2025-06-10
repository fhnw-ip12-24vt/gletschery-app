package ch.fhnw.gletschery.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ch.fhnw.gletschery.model.GameState;
import ch.fhnw.gletschery.model.Player;

import java.util.Arrays;

public class MemorycardCheckerTest {

    private GameState gameState;
    private MemorycardChecker checker;

    @BeforeEach
    public void setup() {
        Player player1 = new Player();
        Player player2 = new Player();
        gameState = new GameState(5, "en", Arrays.asList(player1, player2));
        checker = new MemorycardChecker(gameState);
    }

    @Test
    public void testIsValidCardValidInput() {
        String[] ids = {"00A", "00B"};
        assertTrue(checker.isValidCard(ids));
    }

    @Test
    public void testIsValidCardInvalidLength() {
        String[] ids = {"0A", "00B"};
        assertFalse(checker.isValidCard(ids));
    }

    @Test
    public void testIsValidCardInvalidFormat() {
        String[] ids = {"0XA", "00C"};
        assertFalse(checker.isValidCard(ids));
    }

    @Test
    public void testIsPairTrue() {
        String[] ids = {"01A", "01B"};
        assertTrue(checker.isPair(ids));
    }

    @Test
    public void testIsPairFalseSameSuffix() {
        String[] ids = {"01A", "01A"};
        assertFalse(checker.isPair(ids));
    }

    @Test
    public void testIsPairFalseDifferentPrefix() {
        String[] ids = {"01A", "02B"};
        assertFalse(checker.isPair(ids));
    }

    @Test
    public void testAlreadyFoundTrue() {
        gameState.setPairFound(3);
        assertTrue(checker.alreadyFound(3));
    }

    @Test
    public void testAlreadyFoundFalse() {
        assertFalse(checker.alreadyFound(1));
    }

    @Test
    public void testIsLastPairFoundTrue() {
        for (int i = 0; i < 5; i++) {
            gameState.setPairFound(i);
        }
        assertTrue(checker.islastPairFound());
    }

    @Test
    public void testIsLastPairFoundFalse() {
        gameState.setPairFound(0);
        assertFalse(checker.islastPairFound());
    }
}