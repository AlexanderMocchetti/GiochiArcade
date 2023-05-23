package com.giochi.arcade.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.giochi.arcade.Snake.SnakeDIRECTIONS;

public class SnakeController
{

    private static int score;

    public SnakeDIRECTIONS queryDirectionInput()
    {
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);

        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);

        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if (rightPressed)
            return SnakeDIRECTIONS.RIGHT;
        if (leftPressed)
            return SnakeDIRECTIONS.LEFT;
        if (upPressed)
            return SnakeDIRECTIONS.UP;
        if (downPressed)
            return SnakeDIRECTIONS.DOWN;
        return SnakeDIRECTIONS.NONE;

    }



    public boolean checkForRestart ()
    {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);

    }

    public int getScore ()  {
        return score;
    }

    public void resetScore ()
    {
        score = 0;
    }

    public void increasedScore () {
        score++;
    }
}
