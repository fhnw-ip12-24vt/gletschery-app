package ch.fhnw.gletschery.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setup() {
        player = new Player();
        setPrivateNameMap(player);
    }

    private void setPrivateNameMap(Player player) {
        try {
            Field nameField = Player.class.getDeclaredField("name");
            nameField.setAccessible(true);
            Map<String, String> names = new HashMap<>();
            names.put("en", "Alice");
            names.put("de", "Alina");
            nameField.set(player, names);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInitialPoints() {
        assertEquals(0, player.getPoints());
    }

    @Test
    public void testIncreasePoints() {
        player.increasePoints();
        assertEquals(1, player.getPoints());
    }

    @Test
    public void testSetAndGetPoints() {
        player.setPoints(42);
        assertEquals(42, player.getPoints());
    }

    @Test
    public void testResetPoints() {
        player.setPoints(7);
        player.reset();
        assertEquals(0, player.getPoints());
    }

    @Test
    public void testSetAndGetIcon() {
        player.setIcon("🐧");
        assertEquals("🐧", player.getIcon());
    }

    @Test
    public void testGetNameByLanguage() {
        assertEquals("Alice", player.getName("en"));
        assertEquals("Alina", player.getName("de"));
        assertEquals("Alice", player.getNameByLanguage("en"));
    }

    @Test
    public void testSetName() throws Exception {
        Field nameField = Player.class.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(player, new HashMap<>());

        player.setName("fr", "Aline");
        assertEquals("Aline", player.getName("fr"));
    }
}
