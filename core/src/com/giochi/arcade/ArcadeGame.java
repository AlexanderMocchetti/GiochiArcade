package com.giochi.arcade;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.giochi.arcade.ui.LoadingScreen;
import com.giochi.arcade.ui.MenuScreen;
import com.giochi.arcade.ui.Screens;

public class ArcadeGame extends Game{
    private Batch batch;
    private LoadingScreen loadingScreen;
    private MenuScreen menuScreen;
    @Override
    public void create() {
        batch = new SpriteBatch();
        loadingScreen = new LoadingScreen(this);
        changeScreens(Screens.LOADING);
    }
    public void changeScreens(Screens screen){
        switch(screen){
            case LOADING:
                setScreen(loadingScreen);
                break;
            case MENU:
                if(menuScreen == null)
                    menuScreen = new MenuScreen(this);
                setScreen(menuScreen);
                break;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }

    public Batch getBatch() {
        return batch;
    }
}