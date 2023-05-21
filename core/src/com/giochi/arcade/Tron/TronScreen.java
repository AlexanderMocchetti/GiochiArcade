package com.giochi.arcade.Tron;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.giochi.arcade.OptionWindowScreenAdapter;

public class TronScreen extends ScreenAdapter{

    private Stage stage;

    private Table table;

    private Button buttonPause;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture blueBike = new Texture("blueBike.png");
    private Texture redBike = new Texture("redBike.png");
    private Vector2 player1Position = new Vector2(), player2Position = new Vector2();
    private Vector2 player1Direction = new Vector2(), player2Direction = new Vector2();
    private TronPlayer tronPlayer1 = new TronPlayer(blueBike, player1Position.set(0, 75), player1Direction.set(1, 0), 25);
    private TronPlayer tronPlayer2 = new TronPlayer(redBike, player2Position.set(140, 75), player2Direction.set(-1, 0), 25);
    private TronController input = new TronController(tronPlayer1, tronPlayer2);
    private ShapeRenderer shape = new ShapeRenderer();
    public static final float worldWidth = 640, worldHeight = 480;
    private FitViewport viewport;
    private boolean gameStarted;
    private BitmapFont font;

    @Override
    public void render(float delta){

        /*if(!gameStarted){
            batch.begin();
            stage.act(Math.min(delta, 1 / 30f));
            stage.draw();
            font.draw(batch, "Premere SPAZIO per avviare il gioco", worldWidth / 5, worldHeight / 5);
            batch.end();
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                gameStarted = true;
            }else{
                return;
            }
        }

         */

        batch.begin();

        //Gdx.gl.glClearColor(0,0,0,1);
        ScreenUtils.clear(0, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();

        input.handleInput();

        tronPlayer1.move(delta);
        tronPlayer2.move(delta);

        camera.update();

        tronPlayer1.draw(batch);
        tronPlayer2.draw(batch);


        if(tronPlayer1.checkCollisionWithEnemyLaser(tronPlayer2)){
            font.draw(batch, "il rosso ha vinto!  complimenti!", worldWidth/5, worldHeight/5);
            gamePaused();

        } else if(tronPlayer2.checkCollisionWithEnemyLaser(tronPlayer1)){
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
        tronPlayer1.drawLaser(shape);
        shape.setColor(Color.RED);
        tronPlayer2.drawLaser(shape);
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
        stage.dispose();
        batch.dispose();
        font.dispose();
    }

    @Override
    public void show()
    {



        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set((float) worldWidth / 2 , (float) worldHeight / 2 , 0);
        camera.update();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply(true);
        stage = new Stage(viewport);

        Gdx.input.setInputProcessor(stage);



        table = new Table();

        buttonPause = new TextButton("Pause" , new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));


        buttonPause.setSize(20 ,20);

        buttonPause.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                pause();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new OptionWindowScreenAdapter(getInstance()));
                return true;
            }
        });

        table.setSize(20 ,20);

        table.setFillParent(true);

        table.add(buttonPause);

        table.setPosition(260 ,-220);

        table.pack();

        batch = new SpriteBatch();

        stage.addActor(table);

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
        tronPlayer1.setDirection(0, 0);
        tronPlayer2.setDirection(0, 0);
    }

    public TronScreen getInstance()
    {
        return this;
    }

}
