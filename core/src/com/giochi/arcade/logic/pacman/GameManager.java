package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

public class GameManager {
    public static final GameManager instance = new GameManager();
    public static final float
            pixelToGrid = 1/32f,
            centerTileError = 0.05f;
    private ArrayList<Rectangle> walls;
    private final TiledMap map;
    private GameManager(){
        map = new TmxMapLoader().load("PacmanMap.tmx");
        correctRectangles();
    }

    private void correctRectangles(){
        walls = new ArrayList<>(35);
        Rectangle rect;
        for(MapObject object : map.getLayers().get("CollisionLayer").getObjects()) {
            rect = ((RectangleMapObject) object).getRectangle();
            rect.x *= pixelToGrid;
            rect.y *= pixelToGrid;
            rect.width *= pixelToGrid;
            rect.height *= pixelToGrid;
            walls.add(rect);
        }
    }
    public TiledMap getMap() {
        return map;
    }

    public ArrayList<Rectangle> getWalls(){
        return walls;
    }
}
