package ch.fhnw.gletschery.utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnvironmentVariablesTest {

    @Test
    public void testConstantsAreSetCorrectly() {
        assertEquals("/home/pi/Documents/gletschery_data.json", EnvironmentVariables.JSON_PATH);
        assertEquals(4000, EnvironmentVariables.WAITING_TIME_WRONG_PAIR);
        assertEquals(6000, EnvironmentVariables.WAITING_TIME_PAIR);
        assertEquals(250, EnvironmentVariables.GUI_REFRESH_TIME);
        assertEquals(3000, EnvironmentVariables.DIASHOW_TEXT_CHANGE_INTERVAL);
        assertEquals(9000, EnvironmentVariables.DIASHOW_IMAGE_CHANGE_INTERVAL);
        assertEquals(960, EnvironmentVariables.GUI_CENTER_WIDTH);
        assertEquals(1920, EnvironmentVariables.GAME_WINDOW_WIDTH);
        assertEquals(1080, EnvironmentVariables.GAME_WINDOW_HEIGHT);
        assertEquals("Gletschery", EnvironmentVariables.GAME_WINDOW_TITLE);
        assertEquals(114.0, EnvironmentVariables.TEXT_BOTTOM_LEFT_X);
        assertEquals(1020.0, EnvironmentVariables.TEXT_BOTTOM_LEFT_Y);
        assertEquals(40.0, EnvironmentVariables.ICON_BOTTOM_LEFT_X);
        assertEquals(980.0, EnvironmentVariables.ICON_BOTTOM_LEFT_Y);
        assertEquals(70.0, EnvironmentVariables.UPPER_SPACE_BETWEEN_BUTTON_TEXT);
        assertEquals(65.0, EnvironmentVariables.UPPER_SPACE_BETWEEN_SELECTIONS_MAIN_MENU);
        assertEquals(38, EnvironmentVariables.FONT_SIZE_BUTTONS);
        assertEquals(45, EnvironmentVariables.FONT_SIZE_SELECTIONS_MAIN_MENU);
        assertEquals(80, EnvironmentVariables.FONT_SIZE_START_BUTTON);
        assertTrue(EnvironmentVariables.FULLSCREEN);
        assertEquals("/home/pi/Pictures/generalImages/instruction.png", EnvironmentVariables.INSTRUCTION_IMAGE);
        assertEquals("/home/pi/Pictures/generalImages/introduction.png", EnvironmentVariables.INTRODUCTION_IMAGE);
        assertEquals("/home/pi/Pictures/image.jpg", EnvironmentVariables.IMAGE_FILE_PATH);
        assertEquals("Inter", EnvironmentVariables.FONT_FAMILY);
        assertEquals(2 * 60 * 1000, EnvironmentVariables.TIMEOUT_DURATION_HOMESCREEN);
        assertEquals(5 * 60 * 1000, EnvironmentVariables.TIMEOUT_DURATION_ELSE);
    }
}
