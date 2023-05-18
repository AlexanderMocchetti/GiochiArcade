package com.giochi.arcade;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;

public class ArcadeGame extends Game {
    @Override
    public void create() {

    }

    public void setNewScreen(ScreenAdapter newScreen)
    {
        setScreen(newScreen);
    }

}
