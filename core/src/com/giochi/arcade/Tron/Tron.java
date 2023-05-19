package com.giochi.arcade.Tron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Tron extends ScreenAdapter{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ArrayList<Laser> positions;
    private Texture img1 = new Texture("blueBike.jpg");
    private Texture img2 = new Texture("redBike.jpg");
    private Texture blueLaser = new Texture("blueLaser.png");
    private Texture redLaser = new Texture("redLaser.png");
    //Sprite playerSpriteBlue = new Sprite(img1);
    //Sprite playerSpriteRed = new Sprite(img2);
    //Sprite laserSpriteBlue = new Sprite(blueLaser);
    //Sprite laserSpriteRed = new Sprite(redLaser);
    private Vector2 player1Position = new Vector2(100, 100), player2Position = new Vector2(500, 500);
    private Vector2 player1Direction = new Vector2(0, 0), player2Direction = new Vector2(0, 0);
    private Player player1 = new Player(img1,player1Position.set(100, 100), player1Direction.set(1,0), 1, blueLaser);
    private Player player2 = new Player(img2, player2Position.set(700,200), player2Direction.set(-1,0), 1, redLaser);
    private TronController input = new TronController(player1, player2);

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        input.update();

        batch.begin();


        player1.move();
        player2.move();

        //player1.draw(batch);

        positions.add(new Laser(blueLaser, player1));
        positions.add(new Laser(redLaser, player2));

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.draw(player1.getTexture(), player1.getPosition().x, player1.getPosition().y);
        batch.draw(player2.getTexture(), player2.getPosition().x, player2.getPosition().y);



        batch.end();
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
    public void show() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        positions = new ArrayList<Laser>();

        TronController input = new TronController(player1, player2);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        batch = new SpriteBatch();
    }

    @Override
    public void hide(){
    }

    @Override
    public void pause(){
    }

}
