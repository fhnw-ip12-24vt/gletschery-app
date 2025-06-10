package ch.fhnw.gletschery.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Settings {
    @JsonProperty("languages")
    private List<String> languages;
    private String defaultLanguage;
    @JsonProperty("backgroundImage")
    private String backgroundImage;
    @JsonProperty("logoImage")
    private String logoImage;
    @JsonProperty
    private Credits credits;
    @JsonProperty("introductionImage")
    private String introductionImage;
    @JsonProperty("instructionImage")
    private String instructionImage;

    public Settings() {
        super();
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    } 

    public String getInstructionImage() {
        return instructionImage;
    }

    public void setInstructionImage(String instructionImage) {
        this.instructionImage = instructionImage;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public String getIntroductionImage() {
        return introductionImage;
    }

    public void setIntroductionImage(String introductionImage) {
        this.introductionImage = introductionImage;
    }
}
