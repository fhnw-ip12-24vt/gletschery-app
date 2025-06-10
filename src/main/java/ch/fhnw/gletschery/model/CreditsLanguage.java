package ch.fhnw.gletschery.model;

public class CreditsLanguage {
    private String title;
    private String text;
    private String ePics;
    private String swissEduc;
    private String glacierChange;

    public CreditsLanguage() {
    } 

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setePics(String ePics) {
        this.ePics = ePics;
    }

    public String getSwissEduc() {
        return swissEduc;
    }

    public void setSwissEduc(String swissEduc) {
        this.swissEduc = swissEduc;
    }

    public String getGlacierChange() {
        return glacierChange;
    }

    public void setGlacierChange(String glacierChange) {
        this.glacierChange = glacierChange;
    }
}
