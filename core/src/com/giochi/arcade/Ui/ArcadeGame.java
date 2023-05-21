package com.giochi.arcade.Ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.giochi.arcade.Tron.TronScreen;
import com.giochi.arcade.Pong.PongScreen;

public class ArcadeGame extends Game{
    private Batch batch;
    private LoadingScreen loadingScreen;
    private MenuScreen menuScreen;
    private PongScreen pongScreen;
    private TronScreen tronScreen;
    private ShapeRenderer shape;
    @Override
    public void create() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        tronScreen = new TronScreen();
        pongScreen = new PongScreen();
        loadingScreen = new LoadingScreen(this);
        setScreen(pongScreen);
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