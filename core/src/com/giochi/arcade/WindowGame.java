package com.giochi.arcade;

import com.badlogic.gdx.Game;

public class WindowGame extends Game {
    @Override
    public void create()
    {
        setScreen(new WindowScreenGame());
    }
}
