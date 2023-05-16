package com.giochi.arcade;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;

public class WindowGame extends Game {

    ScreenAdapter screenAdapter = new WindowScreenGame();
    @Override
    public void create()
    {
        setScreen(new WindowScreenGame());
    }
}
