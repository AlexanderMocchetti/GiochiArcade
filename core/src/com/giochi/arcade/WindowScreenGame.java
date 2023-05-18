package com.giochi.arcade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import jdk.javadoc.internal.doclets.toolkit.taglets.snippet.Action;

import java.awt.*;
import java.util.Vector;

public class WindowScreenGame extends ScreenAdapter
{

    private Stage stage;

    private Camera camera;

    private Viewport viewport;

    private Vector<Button> buttonsGames;

    private Button buttonSpaceInvaders , buttonSnake , buttonPacman , buttonPong , buttonTron;

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

        buttonSpaceInvaders = new TextButton("Space invaders" , new Skin(Gdx.files.internal("assets/gdx-skins-master/commodore64/skin/uiskin.json")));

        buttonSnake = new TextButton("Snake" , new Skin(Gdx.files.internal("assets/gdx-skins-master/commodore64/skin/uiskin.json")));

        buttonsGames = new Vector<>(0 ,1);

        camera.update();

        table = new Table();

        table.setFillParent(true);

        table.add(buttonSpaceInvaders);

        table.row();

        table.add(buttonSnake);

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
}
