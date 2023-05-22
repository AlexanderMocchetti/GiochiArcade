package com.giochi.arcade.Ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giochi.arcade.Snake.SnakeScreenGame;
import com.giochi.arcade.SpaceInvaders.SpaceInvadersScreenGame;
import com.giochi.arcade.Tron.TronScreen;
import com.giochi.arcade.Pong.PongScreen;

public class WindowScreenGame extends ScreenAdapter
{

    private Stage stage;

    private Camera camera;

    private Viewport viewport;


    private Button buttonSpaceInvaders , buttonSnake , buttonPacman , buttonPong , buttonTron;

    private Button buttonBack;

    private Table table;

    private static int WORLD_WIDTH = 640;

    private static int WORLD_HEIGHT = 480;



    @Override
    public void show()
    {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();

        viewport = new FitViewport(WORLD_WIDTH , WORLD_HEIGHT , camera);

        buttonSpaceInvaders = new TextButton("Space invaders" , new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));

        buttonSpaceInvaders.addListener(new ChangeListener(){

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new SpaceInvadersScreenGame());

            }
        });
        buttonSnake = new TextButton("Snake" , new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));

        buttonSnake.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new SnakeScreenGame());
            }

        });

        buttonTron = new TextButton("Tron" ,  new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));

        buttonTron.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new TronScreen());

            }
        });

        buttonPong = new TextButton("Pong" , new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));

        buttonPong.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new PongScreen());

            }
        });

        buttonPacman = new TextButton("Pacman" , new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));

        buttonPacman.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new PacmanScreen());
                return true;
            }
        });
        buttonBack = new TextButton("Back" , new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));

        buttonBack.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new StartScreen());
                return true;
            }
        });

        camera.update();

        table = new Table();

        table.setFillParent(true);

        table.add(buttonSpaceInvaders);

        table.row();

        table.add(buttonSnake);

        table.row();

        table.add(buttonTron);

        table.row();

        table.add(buttonPong);

        table.row();

        table.add(buttonPacman);

        table.row();

        table.add(buttonBack);

        stage.addActor(table);
    }
    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
       viewport.update(width , height , true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
