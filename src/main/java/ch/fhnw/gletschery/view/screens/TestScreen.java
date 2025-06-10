package ch.fhnw.gletschery.view.screens;


import ch.trick17.gui.Gui;

public class TestScreen implements Screen {
    private final String text;


    public TestScreen(String a) {
        text = a;
    }

    @Override
    public void draw(Gui window) {

        window.drawString(text, 10, 0);

    }
}