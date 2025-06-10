package ch.fhnw.gletschery.view.dto;

/**
 * Die Klasse {@code InfoScreenDto} stellt ein Data Transfer Object (DTO) dar,
 * das alle relevanten Informationen für den Informationsbildschirm des Spiels enthält.
 * <p>
 * Dazu gehören Spielerinformationen, Kartendaten, Punktestände sowie UI-bezogene Parameter.
 */
public class InfoScreenDto {
    private final String background;
    private String font;
    private final boolean isPair;
    private final String image1;
    private final String image2;
    private final String fact;
    private final String name1;
    private final String name2;
    private final String date1;
    private final String date2;
    private final int player1Points;
    private final String player1Image;
    private final int player2Points;
    private final String player2Image;
    private final String endGame;
    private final String cancel;
    private final String checkmarkIcon;
    private final String forsetzen;


    public InfoScreenDto(String checkmarkIcon,
                         String forsetzen,
                         String cancel,
                         String endGame,
                         String font,
                         int player1Points,
                         int player2Points,
                         String player1Image,
                         String player2Image,
                         String date1,
                         String date2,
                         String image1,
                         String image2,
                         String name1,
                         String name2,
                         String fact,
                         String background,
                         boolean isPair) {

        this.background = background;
        this.font = font;
        this.isPair = isPair;
        this.image1 = image1;
        this.image2 = image2;
        this.fact = fact;
        this.name1 = name1;
        this.name2 = name2;
        this.date1 = date1;
        this.date2 = date2;
        this.player1Points = player1Points;
        this.player1Image = player1Image;
        this.player2Points = player2Points;
        this.player2Image = player2Image;
        this.endGame = endGame;
        this.cancel = cancel;
        this.checkmarkIcon = checkmarkIcon;
        this.forsetzen = forsetzen;

    }

    public boolean isPair() {
        return isPair;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }


    public String getName1() {
        return name1;
    }


    public String getName2() {
        return name2;
    }

    public String getFact() {
        return fact;
    }

    public String getDate1() {
        return date1;
    }

    public String getDate2() {
        return date2;
    }

    public String getBackground() {
        return background;
    }

    public int getPlayer1Points() {
        return player1Points;
    }


    public int getPlayer2Points() {
        return player2Points;
    }

    public String getPlayer1Image() {
        return player1Image;
    }

    public String getPlayer2Image() {
        return player2Image;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getEndGame() {
        return endGame;
    }

    public String getCancel() {
        return cancel;
    }

    public String getCheckmarkIcon() {
        return checkmarkIcon;
    }

    public String getForsetzen() {
        return forsetzen;
    }
}

