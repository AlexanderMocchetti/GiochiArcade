package com.giochi.arcade;

import com.badlogic.gdx.Game;

public class SnakeGame extends Game {
    @Override
    public void create() {
        setScreen(new GameScreenSnake());
    }
}
