package com.giochi.arcade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controller
{
    public DIRECTIONS queryInput ()
    {
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);

        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);

        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if (rightPressed)
            return DIRECTIONS.RIGHT;
        if (leftPressed)
            return DIRECTIONS.LEFT;
        if (upPressed)
            return DIRECTIONS.UP;
        if (downPressed)
            return DIRECTIONS.DOWN;
        return DIRECTIONS.NONE;

    }
}
