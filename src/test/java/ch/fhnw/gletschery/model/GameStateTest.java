package ch.fhnw.gletschery.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Arrays;
import java.util.Optional;

public class GameStateTest {

    private Player player1;
    private Player player2;
    private GameState gameState;

    @BeforeEach
    public void setup() {
        player1 = new Player();
        player2 = new Player();
        gameState = new GameState(5, "de", Arrays.asList(player1, player2));
    }

    @Test
    public void testConstructorInitialization() {
        assertSame(player1, gameState.getPlayer1());
        assertSame(player2, gameState.getPlayer2());
        assertSame(player1, gameState.getActivePlayer());
        assertEquals("de", gameState.getCurrentLanguage());
        assertEquals(5, gameState.getFoundPairs().length);
    }

    @Test
    public void testSetAndGetFoundPairs() {
        gameState.setPairFound(2);
        assertTrue(gameState.getFoundPairs()[2]);
    }

    @Test
    public void testSetAndGetQrResults() {
        String[] results = {"result1", "result2"};
        gameState.setQrResults(results);
        assertArrayEquals(results, gameState.getQrResults());
    }

    @Test
    public void testSetAndGetActivePlayer() {
        gameState.setActivePlayer(player2);
        assertSame(player2, gameState.getActivePlayer());
    }

    @Test
    public void testSetAndGetCurrentLanguage() {
        gameState.setCurrentLanguage("fr");
        assertEquals("fr", gameState.getCurrentLanguage());
    }

    @Test
    public void testGetWinnerPlayer1Wins() {
        player1.setPoints(10);
        player2.setPoints(5);
        Optional<Player> winner = gameState.getWinner();
        assertTrue(winner.isPresent());
        assertSame(player1, winner.get());
    }

    @Test
    public void testGetWinnerPlayer2Wins() {
        player1.setPoints(5);
        player2.setPoints(10);
        Optional<Player> winner = gameState.getWinner();
        assertTrue(winner.isPresent());
        assertSame(player2, winner.get());
    }

    @Test
    public void testGetWinnerDraw() {
        player1.setPoints(7);
        player2.setPoints(7);
        Optional<Player> winner = gameState.getWinner();
        assertTrue(winner.isEmpty());
    }

    @Test
    public void testReset() {
        player1.setPoints(3);
        player2.setPoints(4);
        gameState.setActivePlayer(player2);
        gameState.setPairFound(1);

        gameState.reset();

        assertSame(player1, gameState.getActivePlayer());
        assertEquals(0, player1.getPoints());
        assertEquals(0, player2.getPoints());
        for (boolean found : gameState.getFoundPairs()) {
            assertFalse(found);
        }
    }
}