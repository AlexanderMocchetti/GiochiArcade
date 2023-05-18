package com.giochi.arcade;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;

public class ArcadeGame extends Game {

    private static ArcadeGame game = null;

    public static ArcadeGame getInstance()
    {
        if (game == null)
        {
            game = new ArcadeGame();
        }
        return game;
    }

    private ArcadeGame()
    {
        create();
    }

    @Override
    public void create() {
        setScreen(new StartScreen());
    }

    public void setNewScreen(ScreenAdapter newScreen)
    {
        setScreen(newScreen);
    }

}
