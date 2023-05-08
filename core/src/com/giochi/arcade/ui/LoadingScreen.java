package com.giochi.arcade.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.giochi.arcade.ArcadeGame;
import com.giochi.arcade.controller.PacmanController;
import com.giochi.arcade.logic.pacman.GameManager;
import com.giochi.arcade.logic.pacman.Player;

public class LoadingScreen extends AbstractScreen{
    Sprite sprite;
    PacmanController pacmanController;
    Player player;
    TiledMap map;
    OrthographicCamera cameraControl;
    MapRenderer mapRenderer;
    public static final float WORLD_WIDTH = 17;
    public static final float WORLD_HEIGHT = 19;
    public LoadingScreen(ArcadeGame parent) {
        super(parent);
        map = GameManager.instance.getMap();
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        camera.setToOrtho(false, WORLD_WIDTH * 100, WORLD_HEIGHT * 100);
        cameraControl = new OrthographicCamera();
        player = new Player(1, 1, 0.1f);
        pacmanController = new PacmanController(player);
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1/32f, batch);
        mapRenderer.setView(camera);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(pacmanController);
    }

    @Override
    public void render(float delta) {
        mapRenderer.render();
        batch.setProjectionMatrix(camera.combined);
        player.update(delta);
        batch.begin();
        player.draw(batch);
        batch.end();
        Rectangle rect = player.getRectangle();
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.rect(rect.x, rect.y, rect.width, rect.height);
        shape.end();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.dispose();
        map.dispose();
        sprite.getTexture().dispose();
    }
}