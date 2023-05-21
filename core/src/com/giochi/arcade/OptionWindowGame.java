package com.giochi.arcade;

import com.badlogic.gdx.Game;
import com.giochi.arcade.Snake.SnakeScreenGame;

public class OptionWindowGame extends Game {
    @Override
    public void create() {
        setScreen(new OptionWindowScreenAdapter(new SnakeScreenGame()));
    }
}
