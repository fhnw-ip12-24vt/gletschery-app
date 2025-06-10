package ch.fhnw.gletschery.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CreditsTest {

    @Test
    public void testQrCodeFields() {
        Credits credits = new Credits();
        credits.setQrCodeGlacierChange("qr_glacier.png");
        credits.setQrCodeSwissEduc("qr_swiss.png");
        credits.setQrCodeEPics("qr_epics.png");

        assertEquals("qr_glacier.png", credits.getQrCodeGlacierChange());
        assertEquals("qr_swiss.png", credits.getQrCodeSwissEduc());
        assertEquals("qr_epics.png", credits.getQrCodeEPics());
    }

    @Test
    public void testCreditsLanguageFields() {
        CreditsLanguage de = new CreditsLanguage();
        de.setTitle("DE");
        de.setText("Deutsch");
        de.setePics("ePics DE");
        de.setSwissEduc("SwissEduc DE");
        de.setGlacierChange("GlacierChange DE");

        CreditsLanguage en = new CreditsLanguage();
        en.setTitle("EN");
        en.setText("English");
        en.setePics("ePics EN");
        en.setSwissEduc("SwissEduc EN");
        en.setGlacierChange("GlacierChange EN");

        CreditsLanguage fr = new CreditsLanguage();
        fr.setTitle("FR");
        fr.setText("Französisch");
        fr.setePics("ePics FR");
        fr.setSwissEduc("SwissEduc FR");
        fr.setGlacierChange("GlacierChange FR");

        Credits credits = new Credits();
        credits.setDe(de);
        credits.setEn(en);
        credits.setFr(fr);

        assertSame(de, credits.getDe());
        assertSame(en, credits.getEn());
        assertSame(fr, credits.getFr());

        assertEquals("DE", credits.getDe().getTitle());
        assertEquals("Deutsch", credits.getDe().getText());
        assertEquals("ePics DE", credits.getDe().getePics());
        assertEquals("SwissEduc DE", credits.getDe().getSwissEduc());
        assertEquals("GlacierChange DE", credits.getDe().getGlacierChange());

        assertEquals("EN", credits.getEn().getTitle());
        assertEquals("English", credits.getEn().getText());
        assertEquals("ePics EN", credits.getEn().getePics());
        assertEquals("SwissEduc EN", credits.getEn().getSwissEduc());
        assertEquals("GlacierChange EN", credits.getEn().getGlacierChange());

        assertEquals("FR", credits.getFr().getTitle());
        assertEquals("Französisch", credits.getFr().getText());
        assertEquals("ePics FR", credits.getFr().getePics());
        assertEquals("SwissEduc FR", credits.getFr().getSwissEduc());
        assertEquals("GlacierChange FR", credits.getFr().getGlacierChange());
    }
}
