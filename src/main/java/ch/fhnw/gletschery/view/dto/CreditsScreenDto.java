package ch.fhnw.gletschery.view.dto;

/**
 * Das {@code CreditsScreenDto} ist ein Data Transfer Object (DTO), das alle
 * Informationen für den Anzeige-Bildschirm der Danksagungen und Quellen (Credits)
 * enthält.
 * <p>
 * Es transportiert die relevanten QR-Codes, Titel und Texte, um auf unterstützende
 * Institutionen wie ePics, SwissEduc und GlacierChange hinzuweisen.
 * </p>
 */
public class CreditsScreenDto {

    private final String qrCodeGlacierChange;
    private final String qrCodeSwissEduc;
    private final String qrCodeEPics;
    private final String title;
    private String text;
    private final String ePics;
    private final String swissEduc;
    private final String glacierChange;
    private final String backHome;

    public CreditsScreenDto(String ePics,
                            String glacierChange, String swissEduc,
                            String qrCodeEPics, String qrCodeGlacierChange,
                            String qrCodeSwissEduc, String text, String title,
                            String backHome) {

        this.qrCodeGlacierChange = qrCodeGlacierChange;
        this.qrCodeSwissEduc = qrCodeSwissEduc;
        this.qrCodeEPics = qrCodeEPics;
        this.title = title;
        this.text = text;
        this.ePics = ePics;
        this.swissEduc = swissEduc;
        this.glacierChange = glacierChange;
        this.backHome = backHome;
    }

    public String getQrCodeGlacierChange() {
        return qrCodeGlacierChange;
    }

    public String getQrCodeSwissEduc() {
        return qrCodeSwissEduc;
    }

    public String getQrCodeEPics() {
        return qrCodeEPics;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getePics() {
        return ePics;
    }

    public String getSwissEduc() {
        return swissEduc;
    }

    public String getGlacierChange() {
        return glacierChange;
    }

    public String getBackHome() {
        return backHome;
    }
}
