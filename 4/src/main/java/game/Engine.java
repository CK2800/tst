package game;

import gameobjects.*;
import gameobjects.impl.AppleImpl;
import gameobjects.impl.FacadeImpl;
import gameobjects.impl.SnakeImpl;

import java.awt.*;

public class Engine
{
    int screenWidth, screenHeight, unitSize, delay;
    Graphics stage;
    Facade facade;
    Apple apple;
    Snake snake;
    private static Engine instance = null;
    private Engine(Facade facade, int screenWidth, int screenHeight, int unitSize, int delay, Graphics stage)
    {
        // Q&D let's be defensive.
        if (facade == null ||
            screenWidth < 600 ||
            screenHeight < 600 ||
            unitSize < 5 ||
            delay < 25 ||
            stage == null) throw new IllegalArgumentException("Engine cannot be run under these circumstances!");

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.unitSize = unitSize;
        this.delay = delay;
        this.stage = stage;
        this.facade = facade;
        this.apple = facade.createApple();
        this.snake = facade.createSnake(3);
    }

    public static Engine getInstance(Facade facade, int screenWidth, int screenHeight, int unitSize, int delay, Graphics stage)
    {
        if (instance == null)
            instance = new Engine(facade, screenWidth, screenHeight, unitSize, delay, stage);

        return instance;
    }

}
