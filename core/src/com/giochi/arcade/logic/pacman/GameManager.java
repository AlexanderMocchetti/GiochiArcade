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

public class GameManager implements Disposable {
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
    private final Array<Rectangle> wallBounds;
    private final Array<Pill> pills;
    private final Rectangle gate;
    private final TiledMap map;
    private final TextureAtlas playerAtlas, ghostAtlas;
    private Graph graph;
    private int pillsEaten = 0;
    public GameManager(){
        playerAtlas = new TextureAtlas("pacman.atlas");
        ghostAtlas = new TextureAtlas("ghost.atlas");
        map = new TmxMapLoader().load("PacmanMap1.tmx");
        wallBounds = new Array<>(false, 37);
        pills = new Array<>(false, 130);
        gate = ((RectangleMapObject) map.getLayers().get("GateLayer").getObjects().get("Gate")).getRectangle();
        correctRectangle(gate);
        loadWalls();
        loadPills();
        GraphBuilder graphBuilder = new GraphBuilder(wallBounds);
        graph = graphBuilder.getGraph();
        System.out.println(graph.getShortestPath("1 1", "7 8"));
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
            wallBounds.add(rect);
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
    public Array<Rectangle> getWallBounds(){
        return wallBounds;
    }
    public Rectangle getGate() {
        return gate;
    }
    public TextureAtlas getPlayerAtlas() {
        return playerAtlas;
    }
    public TextureAtlas getGhostAtlas() {
        return ghostAtlas;
    }
    public void setPlayer(Player player){
        for (Pill pill : pills) {
            pill.setPlayer(player);
        }
    }
    public void setGhost(Ghost ghost){

    }
    public Graph getGraph() {
        return graph;
    }
    public void updatePills(float delta){
        for (Pill pill : pills) {
            pill.update(delta);
            if (pill.isEaten())
                pillsEaten++;
        }
    }
    public void drawPills(ShapeRenderer shape){
        for (Pill pill : pills) {
            pill.draw(shape);
        }
    }
    public void update(boolean gameOver){
        if (gameOver){
            
        }
        if (pillsEaten == pills.size){

        }
    }
    public int getPillsEaten() {
        return pillsEaten;
    }
    @Override
    public void dispose() {
        map.dispose();
        playerAtlas.dispose();
        ghostAtlas.dispose();
    }
}
