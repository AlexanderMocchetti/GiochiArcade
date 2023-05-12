package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class GameManager {
    public static final GameManager instance = new GameManager();
    public static final float
            pixelToGrid = 1/32f,
            centerTileError = 0.05f,
            scalePill = 1/4f,
            scalePillBig = 1/2f;
    private ArrayList<Rectangle> walls;
    private Array<Pill> pills;
    private final TiledMap map;
    private final Texture pill = new Texture("spr_pill_0.png");
    private GameManager(){
        map = new TmxMapLoader().load("PacmanMap1.tmx");
        walls = new ArrayList<>(35);
        pills = new Array<>(false, 100);
        loadWalls();
        loadPills();
    }

    private void correctRectangle(Rectangle rectangle){
        rectangle.x *= pixelToGrid;
        rectangle.y *= pixelToGrid;
        rectangle.width *= pixelToGrid;
        rectangle.height *= pixelToGrid;
    }
    private void loadWalls(){
        Rectangle rect;
        for(MapObject object : map.getLayers().get("CollisionLayer").getObjects()) {
            rect = ((RectangleMapObject) object).getRectangle();
            correctRectangle(rect);
            walls.add(rect);
        }
    }
    private void loadPills(){
        Rectangle rect;
        for(MapObject obj: map.getLayers().get("DotLayer").getObjects()){
            rect = ((RectangleMapObject) obj).getRectangle();
            correctRectangle(rect);
            pills.add(new Pill(rect));
        }
    }
    public TiledMap getMap() {
        return map;
    }

    public Array<Pill> getPills() {
        return pills;
    }

    public ArrayList<Rectangle> getWalls(){
        return walls;
    }
}
