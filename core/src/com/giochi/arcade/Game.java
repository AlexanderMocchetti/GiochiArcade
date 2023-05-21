package com.giochi.arcade;

public abstract class Game extends com.badlogic.gdx.Game{
    @Override
    public void render() {
        if (screen != null) screen.render(0.01666666f);
    }
}
