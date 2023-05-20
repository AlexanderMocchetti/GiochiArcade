package com.giochi.arcade.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.giochi.arcade.ArcadeGame;
import com.giochi.arcade.controller.PacmanController;
import com.giochi.arcade.logic.pacman.GameManager;
import com.giochi.arcade.logic.pacman.Ghost;
import com.giochi.arcade.logic.pacman.Map;
import com.giochi.arcade.logic.pacman.Player;
public class PacmanScreen extends AbstractScreen{
    PacmanController pacmanController;
    AssetManager manager;
    Map map;
    OrthographicCamera cameraControl;
    MapRenderer mapRenderer;
    public static final float
            WORLD_WIDTH = 17,
            WORLD_HEIGHT = 19;
    public PacmanScreen(ArcadeGame parent) {
        super(parent);
        manager = new AssetManager();

        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        cameraControl = new OrthographicCamera();
    }

    @Override
    public void show() {
        manager.load("ghost.atlas", TextureAtlas.class);
        manager.load("player.atlas", TextureAtlas.class);
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("PacmanMap1.tmx", TiledMap.class);

        TiledMap tiledMap = manager.get("PacmanMap1.tmx");

        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, GameManager.pixelToGrid, batch);
        mapRenderer.setView(camera);

        map = new Map(manager);
        pacmanController = new PacmanController(map.getPlayer());


        Gdx.input.setInputProcessor(pacmanController);
    }

    @Override
    public void render(float delta) {
        gameManager.update(ghost.checkPlayerCollision());

        mapRenderer.render();

        map.update(delta);
        batch.begin();
        map.draw(batch);
        batch.end();
        gameManager.updatePills(delta);

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.YELLOW);
        gameManager.drawPills(shape);
        shape.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        gameManager.dispose();
    }
}