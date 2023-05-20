package com.giochi.arcade;

import com.badlogic.gdx.Game;

public class OptionWindowGame extends Game {
    @Override
    public void create() {
        setScreen(new OptionWindowScreenAdapter());
    }
}
