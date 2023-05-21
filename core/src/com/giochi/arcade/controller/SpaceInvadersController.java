package com.giochi.arcade.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.giochi.arcade.SpaceInvaders.SpaceInvadersDIRECTIONS;

public class SpaceInvadersController
{

    private int score = 0;

    public SpaceInvadersDIRECTIONS queryInputSpaceInvadersDirections ()
    {
        boolean left = Gdx.input.isKeyPressed(Input.Keys.A);

        boolean right = Gdx.input.isKeyPressed(Input.Keys.D);

        if (right)
            return SpaceInvadersDIRECTIONS.RIGHT;
        if (left)
            return SpaceInvadersDIRECTIONS.LEFT;
        return SpaceInvadersDIRECTIONS.NONE;
    }

    public boolean checkForShoot ()
    {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }

    public boolean checkForRestart ()
    {
        return Gdx.input.isKeyPressed(Input.Keys.S);

    }

    public int getScore ()
    {
        return score;
    }

    public void resetScore ()
    {
        score = 0;
    }

    public void increasedScore ()
    {
        score++;
    }

    public Object setScore (int score)
    {
        this.score = score;
        return null;
    }

}
