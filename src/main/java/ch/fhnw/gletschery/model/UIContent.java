package ch.fhnw.gletschery.model;

import java.util.Map;

public class UIContent {

    private String startGameFromMenu;
    private String confirmSelection;
    private String changeSelection;
    private String currentLanguage;
    private String startGame;
    private String endGame;
    private String continueGame;
    private String showInstructions;
    private String showCredits;
    private String backHome;
    private Map<String, String> gameMessages;
    private String changeLanguage;
    private String pressAnyKey;
    private String scanString;

    private String changeLanguageMenu;
    private String slideShow;

    public String changeLanguage() {
        return changeLanguage;
    }

    public void setChangeLanguage(String changeLanguage) {
        this.changeLanguage = changeLanguage;
    }

    public String getStartGameFromMenu() {
        return startGameFromMenu;
    }

    public void setStartGameFromMenu(String startGameFromMenu) {
        this.startGameFromMenu = startGameFromMenu;
    }

    public String confirmSelection() {
        return confirmSelection;
    }

    public void setConfirmSelection(String confirmSelection) {
        this.confirmSelection = confirmSelection;
    }

    public String changeSelection() {
        return changeSelection;
    }

    public void setChangeSelection(String changeSelection) {
        this.changeSelection = changeSelection;
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    public String startGame() {
        return startGame;
    }

    public void setStartGame(String startGame) {
        this.startGame = startGame;
    }

    public String endGame() {
        return endGame;
    }

    public void setEndGame(String endGame) {
        this.endGame = endGame;
    }

    public Map<String, String> gameMessages() {
        return gameMessages;
    }

    public String getGameMessage(String key) {
        return gameMessages.get(key);
    }

    public String getBackHome() {
        return backHome;
    }

    public void setBackHome(String backHome) {
        this.backHome = backHome;
    }

    public void setGameMessages(Map<String, String> gameMessages) {
        this.gameMessages = gameMessages;
    }

    public String getShowCredits() {
        return showCredits;
    }

    public void setShowCredits(String showCredits) {
        this.showCredits = showCredits;
    }

    public String getShowInstructions() {
        return showInstructions;
    }

    public void setShowInstructions(String showInstructions) {
        this.showInstructions = showInstructions;
    }

    public String getContinueGame() {
        return continueGame;
    }

    public void setContinueGame(String continueGame) {
        this.continueGame = continueGame;
    }

    public String pressAnyKey() {
        return pressAnyKey;
    }

    public void setPressAnyKey(String pressAnyKey) {
        this.pressAnyKey = pressAnyKey;
    }

    public String getChangeLanguageMenu() {
        return changeLanguageMenu;
    }

    public void setChangeLanguageMenu(String changeLanguageMenu) {
        this.changeLanguageMenu = changeLanguageMenu;
    }

    public String getSlideShow() {
        return slideShow;
    }

    public void setSlideShow(String slideShow) {
        this.slideShow = slideShow;
    }

    public String getScanString() {
        return scanString;
    }

    public void setScanString(String scanString) {
        this.scanString = scanString;
    }
}
