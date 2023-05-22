package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;
import com.giochi.arcade.logic.pacman.ai.Graph;
import com.giochi.arcade.logic.pacman.ai.GraphBuilder;
import org.w3c.dom.css.Rect;

public class GameManager implements Disposable {
    public static final GameManager instance = new GameManager();
    public static final float
            pixelToGrid = 1/32f,
            centerTileError = 0.05f,
            scalePill = 1/4f,
            scalePillBig = 0.8f,
            pillIntermittentTime = 0.1f,
            playerAnimationTimeFrame = 0.08f;
    private final Array<Rectangle> walls;
    private final Array<Pill> pills;
    private final Rectangle gate;
    private final TiledMap map;
    private final TextureAtlas atlas;
    private final Logger logger;
    private GameManager(){
        atlas = new TextureAtlas("pacman.atlas");
        map = new TmxMapLoader().load("PacmanMap1.tmx");
        walls = new Array<>(false, 35);
        pills = new Array<>(false, 100);
        gate = ((RectangleMapObject) map.getLayers().get("GateLayer").getObjects().get("Gate")).getRectangle();
        correctRectangle(gate);
        logger = new Logger("WallLogger", Logger.INFO);
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
            logger.info(rect.toString());
            walls.add(rect);
        }
    }
    private void loadPills(){
        Rectangle rect;
        for(MapObject obj: map.getLayers().get("DotLayer").getObjects()){
            rect = ((RectangleMapObject) obj).getRectangle();
            correctRectangle(rect);
            pills.add(new Pill(rect, obj.getProperties().get("KillDot") != null));
        }
    }
    public TiledMap getMap() {
        return map;
    }

    public Array<Pill> getPills() {
        return pills;
    }

    public Array<Rectangle> getWalls(){
        return walls;
    }
    public Rectangle getGate() {
        return gate;
    }
    public TextureAtlas getAtlas() {
        return atlas;
    }
    @Override
    public void dispose() {
        map.dispose();
        atlas.dispose();
    }
}
