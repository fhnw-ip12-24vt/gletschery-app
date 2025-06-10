package ch.fhnw.gletschery;

import ch.fhnw.gletschery.controller.GameController;

public class StartUp {
 
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.start();
    }
}
