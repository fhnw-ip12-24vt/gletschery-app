package ch.fhnw.gletschery.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UI {

    @JsonProperty("checkMarkIcon")
    private String checkMarkIcon;
    @JsonProperty("nextPageIcon")
    private String nextPageIcon;
    @JsonProperty("cancelIcon")
    private String cancelIcon;
    @JsonProperty("pointerIcon")
    private String pointerIcon;
    @JsonProperty("pointerUpIcon")
    private String pointerUpIcon;
    @JsonProperty("germanFlagIcon")
    private String germanFlagIcon;
    @JsonProperty("frenchFlagIcon")
    private String frenchFlagIcon;
    @JsonProperty("englishFlagIcon")
    private String englishFlagIcon;
    @JsonProperty("de")
    private UIContent de;

    @JsonProperty("en")
    private UIContent en;

    @JsonProperty("fr")
    private UIContent fr;

    public UIContent de() {
        return de;
    }

    public UIContent en() {
        return en;
    }

    public UIContent fr() {
        return fr;
    }

    public String getCheckMarkIcon() {
        return checkMarkIcon;
    }

    public String getNextPageIcon() {
        return nextPageIcon;
    }

    public String getCancelIcon() {
        return cancelIcon;
    }

    public void setCheckMarkIcon(String checkMarkIcon) {
        this.checkMarkIcon = checkMarkIcon;
    }

    public void setNextPageIcon(String nextPageIcon) {
        this.nextPageIcon = nextPageIcon;
    }

    public void setCancelIcon(String cancelIcon) {
        this.cancelIcon = cancelIcon;
    }

    public String getPointerIcon() {
        return pointerIcon;
    }

    public void setPointerIcon(String pointerIcon) {
        this.pointerIcon = pointerIcon;
    }

    public String getPointerUpIcon() {
        return pointerUpIcon;
    }

    public void setPointerUpIcon(String pointerUpIcon) {
        this.pointerUpIcon = pointerUpIcon;
    }

    public String getGermanFlagIcon() {
        return germanFlagIcon;
    }

    public void setGermanFlagIcon(String germanFlagIcon) {
        this.germanFlagIcon = germanFlagIcon;
    }

    public String getFrenchFlagIcon() {
        return frenchFlagIcon;
    }

    public void setFrenchFlagIcon(String frenchFlagIcon) {
        this.frenchFlagIcon = frenchFlagIcon;
    }

    public String getEnglishFlagIcon() {
        return englishFlagIcon;
    }

    public void setEnglishFlagIcon(String englishFlagIcon) {
        this.englishFlagIcon = englishFlagIcon;
    }
}
