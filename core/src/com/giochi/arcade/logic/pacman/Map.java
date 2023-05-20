package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.giochi.arcade.logic.pacman.ai.Graph;
import com.giochi.arcade.logic.pacman.ai.GraphBuilder;

public class Map {
    public static final int WALL_NUMBER = 37;
    private final Pills pills = new Pills(this);
    private final Array<Rectangle> wallBounds = new Array<>(false, WALL_NUMBER);
    private final Rectangle gate;
    private final Ghost ghost;
    private final Player player;
    private final TiledMap map;
    private final AssetManager manager;
    private final Graph graph;
    public Map(AssetManager manager){
        this.manager = manager;
        map = manager.get("PacmanMap1.tmx");
        buildMap();
        gate = ((RectangleMapObject) map.getLayers().get("GateLayer").getObjects().get("Gate")).getRectangle();
        GraphBuilder graphBuilder = new GraphBuilder(wallBounds);
        graph = graphBuilder.getGraph();
        ghost = new Ghost();
        player = new Player();
    }
    private void buildMap(){
        loadWalls();
        loadPills();
    }
    private void loadWalls(){
        Rectangle rect;
        for(MapObject object : map.getLayers().get("CollisionLayer").getObjects()) {
            rect = ((RectangleMapObject) object).getRectangle();
            correctRectangle(rect);
            wallBounds.add(rect);
        }
    }
    private void loadPills(){
        Rectangle rect;
        for(MapObject obj: map.getLayers().get("DotLayer").getObjects()){
            rect = ((RectangleMapObject) obj).getRectangle();
            correctRectangle(rect);
            pills.addPill(rect, obj.getProperties().get("KillDot") != null);
        }
    }
    private void correctRectangle(Rectangle rectangle){
        rectangle.x *= GameManager.pixelToGrid;
        rectangle.y *= GameManager.pixelToGrid;
        rectangle.width *= GameManager.pixelToGrid;
        rectangle.height *= GameManager.pixelToGrid;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public Pills getPills() {
        return pills;
    }

    public Player getPlayer() {
        return player;
    }
    public TextureAtlas getPlayerAtlas(){
        return manager.get("pacman.atlas");
    }
    public TextureAtlas getGhostAtlas(){
        return manager.get("ghost.atlas");
    }

    public Graph getGraph() {
        return graph;
    }

    public Array<Rectangle> getWallBounds() {
        return wallBounds;
    }

    public Rectangle getGate() {
        return gate;
    }
}
