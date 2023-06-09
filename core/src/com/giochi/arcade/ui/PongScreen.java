package com.giochi.arcade.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.giochi.arcade.ArcadeGame;
import com.giochi.arcade.logic.pong.Ball;
import com.giochi.arcade.logic.pong.Player;

import java.awt.*;

public class PongScreen extends AbstractScreen{

    public static final float
        WORLD_WIDTH = 300,
        WORLD_HEIGHT = 300;
    private final Ball ball;
    private final Player p1,p2;
    private boolean start;
    private BitmapFont font;
    public PongScreen(ArcadeGame parent) {
        super(parent);
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        p1 = new Player(20,WORLD_HEIGHT/2,5,30,3,1);
        p2 = new Player(WORLD_WIDTH-20,WORLD_HEIGHT/2,5,30,3,2);
        ball = new Ball(WORLD_HEIGHT/2, WORLD_WIDTH / 2, 5F, 3, 1.5f,p1,p2);
        start=false;

    }

    @Override
    public void show() {
        font=new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(0.5f);

    }

    @Override
    public void render(float delta) {
        if(!start){
            batch.begin();
            font.draw(batch, "Premere spazio per avviare il gioco", WORLD_WIDTH / 5, WORLD_HEIGHT / 5);
            batch.end();
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                start = true;
            }else{
                return;
            }
        }
        if(start){
            Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
            shape.begin(ShapeRenderer.ShapeType.Filled);
            ball.update();
            ball.draw(shape);
            p1.update();
            p2.update();
            p1.draw(shape);
            p2.draw(shape);
            shape.end();
        }
    }
}