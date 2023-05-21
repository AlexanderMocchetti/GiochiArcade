package com.giochi.arcade;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.giochi.arcade.ui.PacmanScreen;
import com.giochi.arcade.ui.MenuScreen;
import com.giochi.arcade.ui.PongScreen;
import com.giochi.arcade.ui.Screens;

public class ArcadeGame extends Game{
    private Batch batch;
    private PacmanScreen pacmanScreen;
    private MenuScreen menuScreen;
    private PongScreen pongScreen;
    private ShapeRenderer shape;
    @Override
    public void create() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        pongScreen = new PongScreen(this);
        pacmanScreen = new PacmanScreen(this);
        setScreen(pacmanScreen);
    }
    public void changeScreens(Screens screen){
        switch(screen){
            case LOADING:
                setScreen(pacmanScreen);
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
        shape.dispose();
    }

    public Batch getBatch() {
        return batch;
    }

    public ShapeRenderer getShape() {
        return shape;
    }
}