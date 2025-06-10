package ch.fhnw.gletschery.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UITest {

    @Test
    public void testSetAndGetIcons() {
        UI ui = new UI();

        ui.setCheckMarkIcon("check.png");
        ui.setNextPageIcon("next.png");
        ui.setCancelIcon("cancel.png");
        ui.setPointerIcon("pointer.png");
        ui.setPointerUpIcon("pointerUp.png");
        ui.setGermanFlagIcon("de.png");
        ui.setFrenchFlagIcon("fr.png");
        ui.setEnglishFlagIcon("en.png");

        assertEquals("check.png", ui.getCheckMarkIcon());
        assertEquals("next.png", ui.getNextPageIcon());
        assertEquals("cancel.png", ui.getCancelIcon());
        assertEquals("pointer.png", ui.getPointerIcon());
        assertEquals("pointerUp.png", ui.getPointerUpIcon());
        assertEquals("de.png", ui.getGermanFlagIcon());
        assertEquals("fr.png", ui.getFrenchFlagIcon());
        assertEquals("en.png", ui.getEnglishFlagIcon());
    }

    @Test
    public void testLanguageContentAccessors() throws Exception {
        UI ui = new UI();
        UIContent contentDe = new UIContent();
        UIContent contentEn = new UIContent();
        UIContent contentFr = new UIContent();

        var fieldDe = UI.class.getDeclaredField("de");
        fieldDe.setAccessible(true);
        fieldDe.set(ui, contentDe);

        var fieldEn = UI.class.getDeclaredField("en");
        fieldEn.setAccessible(true);
        fieldEn.set(ui, contentEn);

        var fieldFr = UI.class.getDeclaredField("fr");
        fieldFr.setAccessible(true);
        fieldFr.set(ui, contentFr);

        assertSame(contentDe, ui.de());
        assertSame(contentEn, ui.en());
        assertSame(contentFr, ui.fr());
    }
}
