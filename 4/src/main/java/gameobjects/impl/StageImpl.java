package gameobjects.impl;

import gameobjects.Stage;

public class StageImpl implements Stage
{
    public static final int MIN = 5;
    public static final int MAX = 10;
    private int width, height;


    public StageImpl(int width, int height) throws IllegalArgumentException
    {
        // defensive programming, validate width and height.
        if (width < MIN || height < MIN || width > MAX || height > MAX)
        {
            throw new IllegalArgumentException("Invalid value for width and/or height. Values must be within Stage.MIN and stage.MAX");
        }
        this.width = width;
        this.height = height;

    }
    @Override
    public int getSize()
    {
        return width*height;
    }
}
