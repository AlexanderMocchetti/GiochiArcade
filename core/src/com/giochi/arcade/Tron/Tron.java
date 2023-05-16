package com.giochi.arcade.Tron;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import javax.swing.*;
import java.util.ArrayList;

public class Tron extends ScreenAdapter{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ArrayList<Laser> lasers;
    Texture img1 = new Texture("blueBike.jpg");
    Texture img2 = new Texture("redBike.jpg");
    Vector2 position = new Vector2();
    Vector2 direction = new Vector2();
    Player player1 = new Player(img1,position.set(1000, 600), direction.add(0,1), 3);
    Player player2 = new Player(img2, position.add(1000,500), direction.add(0,1), 3);
  /*  @Override
    public void create(){
        Texture img1 = new Texture("blueBike.png");
        Texture img2 = new Texture("redBike.png");
        Vector2 position = new Vector2();
        Vector2 direction = new Vector2();

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        lasers = new ArrayList<Laser>();
        Player player1 = new Player(img1,position.set(23, 23), direction.add(0,1), 3);
        Player player2 = new Player(img2, position.add(45,45), direction.add(0,1), 3);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        batch = new SpriteBatch();
    }*/

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        delta = Gdx.graphics.getDeltaTime();
        //Player player1 = new Player(img1,position.set(23, 23), direction.add(0,1), 3);
        //Player player2 = new Player(img2, position.add(45,45), direction.add(0,1), 3);
        player1.move();
        player2.move();
        Texture blueLaser = new Texture("blueLaser.png");
        Texture redLaser = new Texture("redLaser.png");

        lasers.add(new Laser(blueLaser));
        lasers.add(new Laser(redLaser));

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
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
        Texture img1 = new Texture("blueBike.jpg");
        Texture img2 = new Texture("redBike.jpg");
        Vector2 position = new Vector2();
        Vector2 direction = new Vector2();

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        lasers = new ArrayList<Laser>();
        //Player player1 = new Player(img1,position.set(23, 23), direction.add(0,1), 3);
        //Player player2 = new Player(img2, position.add(45,45), direction.add(0,1), 3);

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
