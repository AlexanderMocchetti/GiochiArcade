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
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.text.View;
import java.util.ArrayList;

public class Tron extends ScreenAdapter{
    private OrthographicCamera camera;

    private Viewport tronViewPort;
    private SpriteBatch batch;
    private ArrayList<Laser> positions;
    private Texture blueBike;
    private Texture redBike;
    private Texture blueLaser;
    private Texture redLaser;
    //Sprite playerSpriteBlue = new Sprite(img1);
    //Sprite playerSpriteRed = new Sprite(img2);
    //Sprite laserSpriteBlue = new Sprite(blueLaser);
    //Sprite laserSpriteRed = new Sprite(redLaser);
    private Vector2 player1Position;
    private Vector2 player2Position;
    private Vector2 player1Direction;
    private Vector2 player2Direction;
    private Player player1 = new Player(blueBike,player1Position.set(100, 100), player1Direction.set(1,0), 1);
    private Player player2 = new Player(redBike, player2Position.set(700,200), player2Direction.set(-1,0), 1);
    private TronController input = new TronController(player1, player2);
    private ShapeRenderer shape = new ShapeRenderer();




    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        input.update();

        batch.begin();

        player1.move();
        player2.move();


        positions.add(new Laser(blueLaser, player1));
        positions.add(new Laser(redLaser, player2));

        camera.update();

        batch.setProjectionMatrix(camera.combined);
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
    public void show() {


        blueBike = new Texture("blueBike.jpg");

        redBike = new Texture("redBike.jpg");

        blueLaser = new Texture("blueLaser.png");

        redLaser = new Texture("redLaser.png");

        player1Position = new Vector2(100, 100);

        player2Position = new Vector2(500, 500);

        player1Direction = new Vector2(0 , 0);

        player2Direction = new Vector2(0 ,0);

        float width = Gdx.graphics.getWidth();

        float height = Gdx.graphics.getHeight();

        positions = new ArrayList<Laser>();

        TronController input = new TronController(player1, player2);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        batch = new SpriteBatch();
    }

    @Override
    public void resize(int width, int height){
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void dispose(){
        batch.dispose();
    }



    @Override
    public void hide(){
    }

    @Override
    public void pause(){
    }
}
