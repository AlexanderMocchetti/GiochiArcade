package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.giochi.arcade.logic.pacman.ai.Graph;
import com.giochi.arcade.logic.pacman.ai.Node;
import java.util.Deque;

public class Ghost {
    private final Sprite sprite;
    private final Vector2 speedVector, targetSpeedVector;
    private final Graph graph;
    private final Player pacman;
    private final GameManager gameManager;
    private Deque<Node> pathToPacman;
    private Node currentNode, nextNode;
    private final Vector2 positionVector;
    private final Animation<TextureRegion>
            rightAnimation,
            leftAnimation,
            downAnimation,
            upAnimation;
    private Animation<TextureRegion> currentAnimation;
    private TextureRegion currentKeyFrame;
    private float animationStateTime = 0,
                  timeSinceLastFinding = 0;
    private final float speed;
    private boolean disabled = true;
    public Ghost(float x, float y, float width, float height, float speed, Player pacman, GameManager gameManager){
        this.speed = speed;
        this.pacman = pacman;
        this.gameManager = gameManager;
        graph = gameManager.getGraph();
        TextureAtlas atlas = gameManager.getGhostAtlas();
        rightAnimation = new Animation<TextureRegion>(
                GameManager.ghostAnimationTimeFrame,
                atlas.findRegions("rightGhost"),
                Animation.PlayMode.LOOP
        );
        leftAnimation = new Animation<TextureRegion>(
                GameManager.ghostAnimationTimeFrame,
                atlas.findRegions("leftGhost"),
                Animation.PlayMode.LOOP
        );
        upAnimation = new Animation<TextureRegion>(
                GameManager.ghostAnimationTimeFrame,
                atlas.findRegions("upGhost"),
                Animation.PlayMode.LOOP
        );
        downAnimation = new Animation<TextureRegion>(
                GameManager.ghostAnimationTimeFrame,
                atlas.findRegions("downGhost"),
                Animation.PlayMode.LOOP
        );
        currentKeyFrame = atlas.findRegion("rightGhost", 0);
        sprite = new Sprite(currentKeyFrame);
        speedVector = new Vector2();
        targetSpeedVector = new Vector2();
        positionVector = new Vector2(x, y);
        currentNode = graph.getNode((int) x, (int) y);
        sprite.setBounds(x, y, width, height);
        currentAnimation = rightAnimation;
    }
    public void update(float delta){
        animationStateTime += delta;
        currentKeyFrame = currentAnimation.getKeyFrame(animationStateTime);
        sprite.setRegion(currentKeyFrame);
        checkActivation();
        if (disabled)
            return;
        if (checkMovedByTile()) {
            handlePathfinding(delta);
            currentNode = getGridLocation();
            nextNode = pathToPacman.pollFirst();
            if (nextNode == null){
                findNewPath();
                timeSinceLastFinding = 0;
            }
        }
        findTargetSpeed();
        handleAutomatedTurn(delta);
        positionVector.add(speedVector.x * delta, speedVector.y * delta);
        sprite.setPosition(positionVector.x, positionVector.y);
    }
    private void checkActivation(){
        if(gameManager.getPillsEaten() >= GameManager.redGhostActivation && disabled){
            disabled = false;
            findNewPath();
        }
    }
    private void handleAutomatedTurn(float delta){
        float x, y;
        x = sprite.getX() + targetSpeedVector.x * delta;
        y = sprite.getY() + targetSpeedVector.y * delta;
        Rectangle rectangle = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
        if (!PacmanUtils.checkMultipleCollision(rectangle, gameManager.getWallBounds())
                && PacmanUtils.checkCenteredInTile(sprite.getBoundingRectangle())
        ) {
            speedVector.set(targetSpeedVector);
        }
    }
    public Node getGridLocation(){
        return PacmanUtils.getGridLocation(sprite.getBoundingRectangle(), graph);
    }
    private void findTargetSpeed(){
        float deltaX, deltaY;
        deltaX = nextNode.getX() - currentNode.getX();
        if (deltaX == 1)
            currentAnimation = rightAnimation;
        if (deltaX == -1)
            currentAnimation = leftAnimation;
        deltaY = nextNode.getY() - currentNode.getY();
        if (deltaY == 1)
            currentAnimation = upAnimation;
        if (deltaY == -1)
            currentAnimation = downAnimation;
        targetSpeedVector.set(deltaX * speed, deltaY * speed);
    }
    private boolean checkMovedByTile(){
        int currentTileX, currentTileY;
        currentTileX = (int) Math.floor(sprite.getX() + sprite.getWidth() / 2);
        currentTileY = (int) Math.floor(sprite.getY() + sprite.getHeight() / 2);
        return currentTileX != currentNode.getX() || currentTileY != currentNode.getY();
    }
    // TODO: This designs paths outdated by the time, they are queried.
    private void findNewPath(){
        timeSinceLastFinding = 0;
        pathToPacman = graph.getShortestPath(currentNode, pacman.getGridLocation());
        nextNode = pathToPacman.pop();
    }
    private void handlePathfinding(float delta){
        timeSinceLastFinding += delta;
        if (timeSinceLastFinding >= GameManager.ghostIntermittentPathfindingTime){
            findNewPath();
        }
    }
    public boolean checkPlayerCollision(){
        return sprite.getBoundingRectangle().overlaps(pacman.getBounds());
    }
    public Rectangle getBounds(){
        return sprite.getBoundingRectangle();
    }

    public void draw(Batch batch){
        sprite.draw(batch);
    }

}
