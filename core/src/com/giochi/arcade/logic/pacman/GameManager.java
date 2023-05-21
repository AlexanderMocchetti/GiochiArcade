package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.giochi.arcade.logic.pacman.ai.Graph;
import com.giochi.arcade.logic.pacman.ai.GraphBuilder;

public class GameManager {
    public static final float
            pixelToGrid = 1/32f,
            centerTileError = 0.05f,
            scalePill = 1/4f,
            scalePillBig = 0.8f,
            pillIntermittentTime = 0.1f,
            playerAnimationTimeFrame = 0.08f,
            ghostAnimationTimeFrame = 0.12f,
            ghostIntermittentPathfindingTime = 0.5f,
            redGhostActivation = 20;

    private int pacmanLives = 3;
    private int totalPillsEaten = 0;

    public GameManager(){

    }
}
