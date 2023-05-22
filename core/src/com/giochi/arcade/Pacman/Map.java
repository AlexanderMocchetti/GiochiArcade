package com.giochi.arcade.Pacman;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.giochi.arcade.Pacman.ai.*;

public class Map implements Disposable {
    public static final int WALL_NUMBER = 37;
    public static final float SPRITE_LENGTH = 0.95777777f;
    private final Pills pills;
    private final Array<Rectangle> wallBounds = new Array<>(false, WALL_NUMBER);
    private final Rectangle gate;
    private final Ghost ghost;
    private final Player player;
    private final TiledMap map;
    private final Assets manager;
    private final Graph graph;
    private final GameManager gameManager;
    public Map(Assets manager){
        this.manager = manager;
        map = manager.getMap();

        Rectangle ghostSpawn, playerSpawn;

        ghostSpawn = ((RectangleMapObject) map.getLayers().get("GhostSpawnLayer").getObjects().get(0)).getRectangle();
        playerSpawn = ((RectangleMapObject) map.getLayers().get("PlayerSpawnLayer").getObjects().get(0)).getRectangle();

        correctRectangle(ghostSpawn);
        correctRectangle(playerSpawn);

        loadWalls();

        GraphBuilder graphBuilder = new GraphBuilder(wallBounds);
        graph = graphBuilder.getGraph();

        player = new Player(playerSpawn.x, playerSpawn.y, SPRITE_LENGTH, SPRITE_LENGTH, 2.8f, this);
        ghost = new Ghost(ghostSpawn.x, ghostSpawn.y, SPRITE_LENGTH, SPRITE_LENGTH, 3f, this);
        pills = new Pills(this);

        gate = ((RectangleMapObject) map.getLayers().get("GateLayer").getObjects().get("Gate")).getRectangle();
        correctRectangle(gate);

        loadPills();
        gameManager = new GameManager(this);
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

    public void reset(){
        player.reset();
        ghost.reset();
        pills.reset();
    }

    public void update(float delta){
        player.update(delta);
        pills.update(delta);
        ghost.update(delta);
        gameManager.update(delta);
    }
    public void drawShapes(ShapeRenderer shape) {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.YELLOW);
        pills.draw(shape);
        shape.end();
    }

    public void drawSprites(Batch batch) {
        batch.begin();
        player.draw(batch);
        ghost.draw(batch);
        batch.end();
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
        return manager.getPlayerAtlas();
    }
    public TextureAtlas getGhostAtlas(){
        return manager.getGhostAtlas();
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

    public GameManager getGameManager() {
        return gameManager;
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
