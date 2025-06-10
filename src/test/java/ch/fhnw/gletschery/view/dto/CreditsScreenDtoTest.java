package ch.fhnw.gletschery.view.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditsScreenDtoTest {

    @Test
    public void testConstructorAndGetters() {
        CreditsScreenDto dto = new CreditsScreenDto(
                "ePicsContent", "glacierChangeContent", "swissEducContent",
                "qrEPics.png", "qrGlacier.png", "qrSwiss.png",
                "description text", "Credits Title", "backButtonText"
        );

        assertEquals("qrGlacier.png", dto.getQrCodeGlacierChange());
        assertEquals("qrSwiss.png", dto.getQrCodeSwissEduc());
        assertEquals("qrEPics.png", dto.getQrCodeEPics());
        assertEquals("Credits Title", dto.getTitle());
        assertEquals("description text", dto.getText());
        assertEquals("ePicsContent", dto.getePics());
        assertEquals("swissEducContent", dto.getSwissEduc());
        assertEquals("glacierChangeContent", dto.getGlacierChange());
        assertEquals("backButtonText", dto.getBackHome());
    }

    @Test
    public void testSetters() {
        CreditsScreenDto dto = new CreditsScreenDto("", "", "", "", "", "", "", "", "");


        dto.setText("Text");
        assertEquals("Text", dto.getText());
    }
}
