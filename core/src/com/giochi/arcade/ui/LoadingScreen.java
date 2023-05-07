package com.giochi.arcade.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.giochi.arcade.ArcadeGame;
import com.giochi.arcade.controller.PacmanController;
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
        map = new TmxMapLoader().load("PacmanMap.tmx");
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        cameraControl = new OrthographicCamera();
        player = new Player(1, 1, 0.001f, map);
        pacmanController = new PacmanController(player);
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1/32f, parent.getBatch());
        mapRenderer.setView(camera);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(pacmanController);
    }

    @Override
    public void render(float delta) {
        mapRenderer.render();
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