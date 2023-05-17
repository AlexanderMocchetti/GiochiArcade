package com.giochi.arcade.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.giochi.arcade.ArcadeGame;
import com.giochi.arcade.controller.PacmanController;
import com.giochi.arcade.logic.pacman.GameManager;
import com.giochi.arcade.logic.pacman.Ghost;
import com.giochi.arcade.logic.pacman.Pill;
import com.giochi.arcade.logic.pacman.Player;
import com.giochi.arcade.logic.pacman.ai.Graph;
import com.giochi.arcade.logic.pacman.ai.GraphBuilder;

public class PacmanScreen extends AbstractScreen{
    PacmanController pacmanController;
    Player player;
    Ghost ghost;
    GameManager gameManager;
    TiledMap map;
    OrthographicCamera cameraControl;
    MapRenderer mapRenderer;
    public static final float
            WORLD_WIDTH = 17,
            WORLD_HEIGHT = 19;
    public PacmanScreen(ArcadeGame parent) {
        super(parent);
        gameManager = new GameManager();
        map = gameManager.getMap();

        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        cameraControl = new OrthographicCamera();

        player = new Player(1, 1, 0.93777777f, 0.9377777f, 4, gameManager);
        pacmanController = new PacmanController(player);

        gameManager.setPlayer(player);

        ghost = new Ghost(7, 8, 0.93777777f, 0.93777777f, 3, player, gameManager);

        mapRenderer = new OrthogonalTiledMapRenderer(map, GameManager.pixelToGrid, batch);
        mapRenderer.setView(camera);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(pacmanController);
    }

    @Override
    public void render(float delta) {
        mapRenderer.render();

        player.update(delta);
        ghost.update(delta);

        batch.begin();
        player.draw(batch);
        ghost.draw(batch);
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