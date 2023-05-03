package com.giochi.arcade.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.giochi.arcade.ArcadeGame;
import com.giochi.arcade.logic.pong.Ball;

public class PongScreen extends AbstractScreen{
    public static final float WORLD_WIDTH = 200;
    public static final float WORLD_HEIGHT = 200;
    private final Ball ball;
    public PongScreen(ArcadeGame parent) {
        super(parent);
        ball = new Ball(60, WORLD_WIDTH / 2, 10, 4, 4);
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.update(delta);
        ball.draw(shape);
        shape.end();
    }
}
