package ch.fhnw.gletschery.model;

public class Credits {
    private String qrCodeGlacierChange;
    private String qrCodeSwissEduc;
    private String qrCodeEPics;
    private CreditsLanguage de;
    private CreditsLanguage en;
    private CreditsLanguage fr;


    public Credits() {
    } 

    public String getQrCodeGlacierChange() {
        return qrCodeGlacierChange;
    }

    public void setQrCodeGlacierChange(String qrCodeGlacierChange) {
        this.qrCodeGlacierChange = qrCodeGlacierChange;
    }

    public String getQrCodeSwissEduc() {
        return qrCodeSwissEduc;
    }

    public void setQrCodeSwissEduc(String qrCodeSwissEduc) {
        this.qrCodeSwissEduc = qrCodeSwissEduc;
    }

    public String getQrCodeEPics() {
        return qrCodeEPics;
    }

    public void setQrCodeEPics(String qrCodeEPics) {
        this.qrCodeEPics = qrCodeEPics;
    }

    public CreditsLanguage getDe() {
        return de;
    }

    public void setDe(CreditsLanguage de) {
        this.de = de;
    }

    public CreditsLanguage getEn() {
        return en;
    }

    public void setEn(CreditsLanguage en) {
        this.en = en;
    }

    public CreditsLanguage getFr() {
        return fr;
    }

    public void setFr(CreditsLanguage fr) {
        this.fr = fr;
    }
}
