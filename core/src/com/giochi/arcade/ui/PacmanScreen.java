package com.giochi.arcade.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.giochi.arcade.ArcadeGame;
import com.giochi.arcade.controller.PacmanController;
import com.giochi.arcade.logic.pacman.Assets;
import com.giochi.arcade.logic.pacman.GameManager;
import com.giochi.arcade.logic.pacman.Map;
public class PacmanScreen extends AbstractScreen{
    PacmanController pacmanController;
    GameManager gameManager;
    Assets manager;
    Map map;
    OrthographicCamera cameraControl;
    MapRenderer mapRenderer;
    public static final float
            WORLD_WIDTH = 17,
            WORLD_HEIGHT = 19;
    public PacmanScreen(ArcadeGame parent) {
        super(parent);
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        cameraControl = new OrthographicCamera();
    }

    @Override
    public void show() {
        manager = new Assets();

        TiledMap tiledMap = manager.getMap();

        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, GameManager.pixelToGrid, batch);
        mapRenderer.setView(camera);

        map = new Map(manager);
        pacmanController = new PacmanController(map.getPlayer());

        Gdx.input.setInputProcessor(pacmanController);
    }

    @Override
    public void render(float delta) {
        mapRenderer.render();

        map.update(delta);

        map.drawSprites(batch);
        map.drawShapes(shape);

        // TODO: add gameManager here
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}