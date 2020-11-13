package ui;

import gameobjects.Facade;
import gameobjects.impl.FacadeImpl;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
    public GameFrame(String title, int defaultCloseOperation, boolean resizable, boolean visible, Component component,
                     int screenWidth, int screenHeight, int unitSize, boolean showGrid, int delay, Facade facade)
    {
        // Q&D let's be defensive.
        if (screenWidth < 600 ||
            screenHeight < 600 ||
            unitSize < 5 ||
            delay < 25) throw new IllegalArgumentException("GameFrame cannot be run under these circumstances!");

        // relay args to GamePanel.
        this.add(new GamePanel(screenWidth, screenHeight, unitSize, showGrid, delay, facade));


        this.setTitle(title);
        this.setDefaultCloseOperation(defaultCloseOperation);
        this.setResizable(resizable);
        this.pack();
        this.setVisible(visible);
        this.setLocationRelativeTo(component);
    }

    public static void main(String[] args)
    {
        GameFrame frame = new GameFrame("snake game",
                                        JFrame.EXIT_ON_CLOSE,
                                        false,
                                        true,
                                        null,
                                        600,
                                        600,
                                        25,
                                        true,
                                        75,
                                        new FacadeImpl());
    }
}