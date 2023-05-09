package com.giochi.arcade.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.giochi.arcade.ArcadeGame;
import com.giochi.arcade.logic.pong.Ball;
import com.giochi.arcade.logic.pong.Player;

public class PongScreen extends AbstractScreen{
    public static final float
        WORLD_WIDTH = 200,
        WORLD_HEIGHT = 200;
    private final Ball ball;
    private final Player p1,p2;
    public PongScreen(ArcadeGame parent) {
        super(parent);
        ball = new Ball(WORLD_HEIGHT/2, WORLD_WIDTH / 2, 5, 2, 2);
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        p1 = new Player(20,WORLD_HEIGHT/2,5,50,1,1);
        p2 = new Player(WORLD_WIDTH-20,WORLD_HEIGHT/2,5,50,1,2);
    }
    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.update(delta);
        ball.draw(shape);
        p1.update();
        p2.update();
        p1.draw(shape);
        p2.draw(shape);
        shape.end();
    }
}