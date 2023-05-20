package com.giochi.arcade.Tron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class Tron extends ScreenAdapter{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    //private ArrayList<Laser> positions;
    private Texture blueBike = new Texture("blueBike.png");
    private Texture redBike = new Texture("redBike.png");
    private Vector2 player1Position = new Vector2(), player2Position = new Vector2();
    private Vector2 player1Direction = new Vector2(), player2Direction = new Vector2();
    private Player player1 = new Player(blueBike, player1Position.set(10, 50), player1Direction.set(1, 0), 25);
    private Player player2 = new Player(redBike, player2Position.set(90, 50), player2Direction.set(-1, 0), 25);
    private TronController input = new TronController(player1, player2);
    private ShapeRenderer shape = new ShapeRenderer();
    public static final float worldWidth = 100, worldHeight = 100;
    private FitViewport viewport;

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        input.handleInput();

        batch.begin();

        player1.move(delta);
        player2.move(delta);

        camera.update();

        player1.draw(batch);
        player2.draw(batch);

        batch.end();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BLUE);
        player1.drawLaser(shape);
        shape.setColor(Color.RED);
        player2.drawLaser(shape);
        shape.end();
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width,height);
        batch.setProjectionMatrix(camera.combined);
        shape.setProjectionMatrix(camera.combined);
    }

    @Override
    public void dispose(){
        batch.dispose();
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(worldWidth, worldHeight);
        camera.setToOrtho(false, worldWidth, worldHeight);
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply(true);

        batch = new SpriteBatch();
    }

    @Override
    public void hide(){
    }

    @Override
    public void pause(){
    }
}
