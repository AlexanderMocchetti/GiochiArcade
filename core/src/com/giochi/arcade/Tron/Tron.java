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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Tron extends ScreenAdapter{

    private Stage stage;

    private Table table;

    private Button buttonPause;
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
    private boolean gameStarted, gameOver;
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

        input.handleInput();

        player1.move(delta);
        player2.move(delta);

        camera.update();

        player1.draw(batch);
        player2.draw(batch);


        if(player1.checkCollisionWithEnemyLaser(player2)){
            font.draw(batch, "il rosso ha vinto!  complimenti!", worldWidth/5, worldHeight/5);
            gamePaused();

        } else if(player2.checkCollisionWithEnemyLaser(player1)){
            font.draw(batch, "il blu ha vinto!  complimenti!", worldWidth/5, worldHeight/5);
            gamePaused();
        }
        /*else if(player2.checkCollisionWithEnemyLaser(player2)){
            font.draw(batch, "il blu ha vinto!  complimenti!", worldWidth/5, worldHeight/5);
            gamePaused();
        } else if(player1.checkCollisionWithEnemyLaser(player1)){
            font.draw(batch, "il blu ha vinto!  complimenti!", worldWidth/5, worldHeight/5);
            gamePaused();
        }*/


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
    public void show()
    {



        camera = new OrthographicCamera(worldWidth, worldHeight);
        camera.setToOrtho(false, worldWidth, worldHeight);
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply(true);
        stage = new Stage(viewport);

        table = new Table();

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
