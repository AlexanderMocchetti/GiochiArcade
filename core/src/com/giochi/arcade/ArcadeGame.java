package com.giochi.arcade;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.giochi.arcade.Tron.Tron;
import com.giochi.arcade.ui.LoadingScreen;
import com.giochi.arcade.ui.MenuScreen;
import com.giochi.arcade.ui.PongScreen;
import com.giochi.arcade.ui.Screens;

public class ArcadeGame extends Game{
    private Batch batch;
    private LoadingScreen loadingScreen;
    private MenuScreen menuScreen;
    private PongScreen pongScreen;
    private Tron tron;
    private ShapeRenderer shape;
    @Override
    public void create() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        tron = new Tron();
        pongScreen = new PongScreen(this);
        loadingScreen = new LoadingScreen(this);
        setScreen(tron);
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

    public ShapeRenderer getShape() {
        return shape;
    }
}