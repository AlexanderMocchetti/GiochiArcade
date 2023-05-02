package com.giochi.arcade.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.giochi.arcade.ArcadeGame;
import com.giochi.arcade.logic.pong.Ball;

public class PongScreen extends AbstractScreen{
    private Ball ball;
    public PongScreen(ArcadeGame parent) {
        super(parent);
        WORLD_WIDTH = 200;
        WORLD_HEIGHT = 200;
        viewport.setWorldSize(WORLD_WIDTH, WORLD_HEIGHT);
        ball = new Ball(60, WORLD_WIDTH / 2, 10, 4, 4);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.update(delta);
        ball.draw(shape);
        shape.end();
    }

    @Override
    public void hide() {

    }
}
