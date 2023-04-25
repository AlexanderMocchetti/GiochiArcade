package com.giochi.arcade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class GameScreenSnake extends ScreenAdapter {

    private GlyphLayout layout;

    private BitmapFont bitmap;

    private Batch batch;

    private ShapeRenderer shapeRenderer;

    private Snake snake;

    private Controller controller;

    // costants

    private static final int SNAKE_SIZE = 32;

    private static final int SNAKE_STEP = SNAKE_SIZE;

    private static final float SNAKE_SPEED = 1F;

    @Override
    public void show() {
        layout = new GlyphLayout();

        bitmap = new BitmapFont();

        batch = new SpriteBatch();

        shapeRenderer = new ShapeRenderer();

        snake = new Snake(2 , 4);

        controller = new Controller();
    }



    private void clearScreen ()
    {
        Gdx.gl.glClearColor(Color.BLACK.getRed() , Color.BLACK.getGreen() , Color.BLACK.getBlue() , Color.BLACK.getAlpha());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void render(float delta)
    {
        snake.updateDirection(controller.queryInput());

        snake.Update(delta);

        clearScreen();

        batch.begin(); // start drawing

        snake.Draw(batch , shapeRenderer);

        batch.end(); // end drawing

    }



    private void queryInput ()
    {
        DIRECTIONS direction = controller.queryInput();
        if (direction != DIRECTIONS.NONE)
        {
            snake.updateDirection(direction);
        }
    }
}
