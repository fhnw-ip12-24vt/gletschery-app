package ch.fhnw.gletschery.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DesignTest {

    @Test
    public void testSetAndGetThemeColor() {
        Design design = new Design();
        design.setThemeColor("#FFFFFF");
        assertEquals("#FFFFFF", design.getThemeColor());
    }

    @Test
    public void testSetAndGetFontSize() {
        Design design = new Design();
        design.setFontSize("14px");
        assertEquals("14px", design.getFontSize());
    }

    @Test
    public void testSetAndGetHighlightColor() {
        Design design = new Design();
        design.setHighlightColor("#FF0000");
        assertEquals("#FF0000", design.getHighlightColor());
    }

    @Test
    public void testSetAndGetFont() {
        Design design = new Design();
        design.setFont("Arial");
        assertEquals("Arial", design.getFont());
    }
}
