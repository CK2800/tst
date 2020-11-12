package ui;

import gameobjects.constants.Direction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeKeyAdapter extends KeyAdapter
{
    private Direction currentDirection = Direction.NONE;
    public Direction getCurrentDirection()
    {
        return currentDirection;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        super.keyPressed(e);

        setCurrentDirection(e.getKeyCode());
    }

    public void setCurrentDirection(int keyCode)
    {
        switch(keyCode)
        {
            case(KeyEvent.VK_UP):
                currentDirection = Direction.UP;
                break;
            case(KeyEvent.VK_DOWN):
                currentDirection = Direction.DOWN;
                break;
            case (KeyEvent.VK_LEFT):
                currentDirection = Direction.LEFT;
                break;
            case (KeyEvent.VK_RIGHT):
                currentDirection = Direction.RIGHT;
                break;
            default:
                break;
        }
    }
}
