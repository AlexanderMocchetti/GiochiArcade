package com.giochi.arcade.Ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.giochi.arcade.Pacman.GameManager;
import com.giochi.arcade.Controller.PacmanController;
import com.giochi.arcade.Pacman.*;
public class PacmanScreen extends AbstractScreen{
    PacmanController pacmanController;

    private Stage stage;

    private Button buttonPause;


    Assets manager;
    Map map;
    OrthographicCamera cameraControl;
    MapRenderer mapRenderer;
    public static final float
            WORLD_WIDTH = 17,
            WORLD_HEIGHT = 19;
    public PacmanScreen() {
        super();
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        cameraControl = new OrthographicCamera();
    }

    @Override
    public void show() {

        stage = new Stage(viewport);

        manager = new Assets();

        TiledMap tiledMap = manager.getMap();

        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, GameManager.pixelToGrid, batch);
        mapRenderer.setView(camera);

        map = new Map(manager);
        pacmanController = new PacmanController(map.getPlayer());

        buttonPause = new TextButton("Pause" , new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));
        buttonPause.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pause();
               ((Game) Gdx.app.getApplicationListener()).setScreen(new OptionWindowScreenAdapter(new OptionWindowScreenAdapter(getInstance())));
                return true;
            }
        });
        Table table = new Table();
        table.add(buttonPause);
        table.setPosition(WORLD_WIDTH /2, WORLD_HEIGHT /2);
        table.pack();
        stage.addActor(table);
        InputMultiplexer inputProcessors = new InputMultiplexer(pacmanController, stage);
        Gdx.input.setInputProcessor(inputProcessors);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
        mapRenderer.render();

        map.update(delta);

        map.drawSprites(batch);
        map.drawShapes(shape);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public PacmanScreen getInstance()
    {
        return this;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
        shape.setProjectionMatrix(camera.combined);
    }
}