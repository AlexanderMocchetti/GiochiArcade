package com.giochi.arcade.Tron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Tron extends ScreenAdapter{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture blueBike = new Texture("blueBike.png");
    private Texture redBike = new Texture("redBike.png");
    private Vector2 player1Position = new Vector2(), player2Position = new Vector2();
    private Vector2 player1Direction = new Vector2(), player2Direction = new Vector2();
    private Player player1 = new Player(blueBike, player1Position.set(0, 75), player1Direction.set(1, 0), 25);
    private Player player2 = new Player(redBike, player2Position.set(140, 75), player2Direction.set(-1, 0), 25);
    private TronController input = new TronController(player1, player2);
    private ShapeRenderer shape = new ShapeRenderer();
    public static final float worldWidth = 150, worldHeight = 150;
    private FitViewport viewport;
    private boolean gameStarted, gameOver = false;
    private BitmapFont font;

    @Override
    public void render(float delta){

        if(!gameStarted){
            batch.begin();
            font.draw(batch, "Premere SPAZIO per avviare il gioco", worldWidth / 5, worldHeight / 5);
            batch.end();
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                gameStarted = true;
            }else{
                return;
            }
        }

        batch.begin();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        input.handleInput(gameOver);

        player1.move(delta);
        player2.move(delta);

        camera.update();

        player1.draw(batch);
        player2.draw(batch);


        if(player1.checkCollisionWithEnemyLaser(player2)){
            gameOver = true;
            font.draw(batch, "il rosso ha vinto!  complimenti!", worldWidth/5, 120);
            gamePaused();
            font.draw(batch, "premere R per riavviare il gioco", 30, worldHeight/5);
            if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
                player1.reset();
                player2.reset();
                player1Position.set(0, 75);
                player2Position.set(140, 75);
                player1.setDirection(1, 0);
                player2.setDirection(-1, 0);
                player1.setRotation(0);
                player2.setRotation(0);
                gameOver = false;
            }
            font.draw(batch, "premere E per chiudere il gioco", 10, 50);
            if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
                Gdx.app.exit();
            }


        } else if(player2.checkCollisionWithEnemyLaser(player1)){
            gameOver = true;
            font.draw(batch, "il blu ha vinto!  complimenti!", worldWidth/5, 120);
            gamePaused();
            font.draw(batch, "premere R per riavviare il gioco", 30, worldHeight/5);
            if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
                player1.reset();
                player2.reset();
                player1Position.set(0, 75);
                player2Position.set(140, 75);
                player1.setDirection(1, 0);
                player2.setDirection(-1, 0);
                player1.setRotation(0);
                player2.setRotation(0);
                gameOver = false;
            }
            font.draw(batch, "premere E per chiudere il gioco", 10, 50);
            if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
                Gdx.app.exit();
            }

        }

        /*if(player1Position.x == 150){
            player1Position.x = 0;
        }
        if(player2Position.x == 150){
            player2Position.x = 0;
        }
        if(player1Position.y == 150){
            player1Position.y = 0;
        }
        if(player2Position.y == 150){
            player2Position.y = 0;
        }
        if(player1Position.x == 0){
            player1Position.x = 150;
        }
        if(player2Position.x == 0){
            player2Position.x = 150;
        }
        if(player1Position.y == 0){
            player1Position.y = 150;
        }
        if(player2Position.y == 0){
            player2Position.y = 150;
        }
        */

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
        font.dispose();
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(worldWidth, worldHeight);
        camera.setToOrtho(false, worldWidth, worldHeight);
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply(true);

        batch = new SpriteBatch();

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(0.5f);
    }

    @Override
    public void hide(){
    }

    @Override
    public void pause(){
    }

    public void gamePaused(){
        player1.setDirection(0, 0);
        player2.setDirection(0, 0);
    }

}
