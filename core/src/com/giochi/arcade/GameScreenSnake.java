package com.giochi.arcade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class GameScreenSnake extends ScreenAdapter { // Main class for snake game

    private GlyphLayout layout;

    private BitmapFont bitmap; // font for score text

    private Batch batch;

    private Camera camera;

    private Viewport viewport;



    private ShapeRenderer shapeRenderer;

    private Snake snake;

    private Food food;

    private Controller controller;

    private STATE state = STATE.PLAYING;

    // costants

    private static final int SNAKE_SIZE = 2; // Size larger than 10 could generate crashes

    private static final int SNAKE_STEP = SNAKE_SIZE;

    private static final int SNAKE_SPEED = 4;

    private static final int WORLD_WIDTH = 640;

    private static final int WORLD_HEIGHT = 480;

    private static final String GAME_OVER_TEXT = "Game Over... Press space to restart!";

    @Override
    public void show() {



        camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());

        camera.position.set(WORLD_WIDTH / 2 , WORLD_HEIGHT / 2 , 0);

        camera.update();

        viewport = new FitViewport(WORLD_WIDTH , WORLD_HEIGHT , camera);

        layout = new GlyphLayout();

        bitmap = new BitmapFont();

        batch = new SpriteBatch();

        shapeRenderer = new ShapeRenderer();

        controller = new Controller();

        snake = new Snake(SNAKE_SIZE , SNAKE_SPEED);

        snake.setSnakeViewPort(viewport);

        food = new Food(snake , controller);

        food.setFoodViewPort(viewport);


    }



    private void clearScreen ()
    {
        Gdx.gl.glClearColor(Color.BLACK.getRed() , Color.BLACK.getGreen() , Color.BLACK.getBlue() , Color.BLACK.getAlpha());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void render(float delta)
    {
        switch (state) {
            case PLAYING: {
                snake.updateDirection(controller.queryInput());

                state = snake.Update(delta);

                food.updatePosition();

                food.checkFoodCollision();


            }
            break;
            case GAME_OVER: {

                if (controller.checkForRestart())
                {
                    restart();
                }
            }
            break;
        }
        clearScreen();
        drawScreen();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width , height);
    }

    public void restart ()
    {
        state = STATE.PLAYING;

        food.reset();

        snake.reset();

        controller.resetScore();

    }
    public void drawScreen ()
    {

        batch.setProjectionMatrix(camera.projection);

        batch.setTransformMatrix(camera.view);

        batch.begin(); // start drawing

        snake.Draw(shapeRenderer);

        snake.drawBodyParts(shapeRenderer);

        food.Draw(shapeRenderer);

        drawGrid(shapeRenderer);

        batch.end();

        batch.begin();

        if (state == STATE.GAME_OVER)
        {
            layout.setText(bitmap , GAME_OVER_TEXT);

            bitmap.draw(batch , GAME_OVER_TEXT ,  viewport.getWorldWidth() / 2 - layout.width / 2 , viewport.getWorldHeight() / 2 - layout.height / 2);


        }

        drawScore();

        batch.end(); // end drawing
    }

    public void drawGrid (@NotNull ShapeRenderer shapeRenderer)
    {

        shapeRenderer.setProjectionMatrix(camera.projection);

        shapeRenderer.setTransformMatrix(camera.view);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.DARK_GRAY);

        for (int i = 0 ; i < viewport.getWorldWidth(); i+= SNAKE_SIZE)
        {
            for (int j = 0 ; j < viewport.getWorldHeight(); j+= SNAKE_SIZE)
            {
                shapeRenderer.rect(i , j , SNAKE_SIZE , SNAKE_SIZE);
            }
        }
        shapeRenderer.end();

    }

    public void drawScore ()
    {
        if (state == STATE.PLAYING)
        {
            String scoreToString = Integer.toString(controller.getScore());
            layout.setText(bitmap , scoreToString);
            bitmap.draw(batch , "Score: " + scoreToString , layout.width,  viewport.getWorldHeight() - layout.height / 2);
        }
    }
}
