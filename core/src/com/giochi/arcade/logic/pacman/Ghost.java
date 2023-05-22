package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.giochi.arcade.logic.pacman.ai.Graph;
import com.giochi.arcade.logic.pacman.ai.Node;

import java.util.Deque;
import java.util.Random;

public class Ghost {
    private final Sprite sprite;
    private final Vector2 speedVector, targetSpeedVector;
    private final Graph graph;
    private final Player pacman;
    private final Map parent;
    private Deque<Node> pathToPacman;
    private Node currentNode, nextNode;
    private final Vector2 positionVector, targetPositionVector;
    private final Vector2 spawnPositionVector;
    private final Animation<TextureRegion>
            rightAnimation,
            leftAnimation,
            downAnimation,
            upAnimation;
    private Animation<TextureRegion> currentAnimation;
    private TextureRegion currentKeyFrame;
    private final TextureRegion scaredGhost;
    private float animationStateTime = 0,
                  timeSinceLastFinding = 0;
    private float speed;
    private final float fullSpeed;
    private boolean disabled = true;
    private boolean scared = false;
    private boolean eaten = false;
    private final Random randomGenerator;
    public Ghost(float x, float y, float width, float height, float speed, Map parent){
        fullSpeed = speed;
        this.speed = speed;
        this.parent = parent;
        graph = parent.getGraph();
        pacman = parent.getPlayer();
        TextureAtlas atlas = parent.getGhostAtlas();
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
        scaredGhost = atlas.findRegion("scaredGhost");
        sprite = new Sprite(currentKeyFrame);
        speedVector = new Vector2();
        targetSpeedVector = new Vector2();
        spawnPositionVector = new Vector2(x, y);
        positionVector = new Vector2(x, y);
        targetPositionVector = new Vector2(positionVector);
        currentNode = graph.getNode((int) x, (int) y);
        sprite.setBounds(x, y, width, height);
        currentAnimation = rightAnimation;
        randomGenerator = new Random();
    }
    public void update(float delta){
        if (eaten) {
            softReset();
            eaten = false;
        }
        scared = parent.getGameManager().isPacmanInvincible();
        if (scared) {
            speed = GameManager.ghostScaredSpeed;
            currentKeyFrame = scaredGhost;
        } else {
            speed = fullSpeed;
            animationStateTime += delta;
            currentKeyFrame = currentAnimation.getKeyFrame(animationStateTime);
        }
        sprite.setRegion(currentKeyFrame);
        checkActivation();
        if (disabled)
            return;
        if (checkMovedByTile() && !scared) {
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
        if(parent.getPills().getPillsEaten() >= GameManager.redGhostActivation && disabled){
            disabled = false;
            findNewPath();
        }
    }
    private void handleAutomatedTurn(float delta){
        float x, y;
        x = sprite.getX() + targetSpeedVector.x * delta;
        y = sprite.getY() + targetSpeedVector.y * delta;
        Rectangle rectangle = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
        if (!PacmanUtils.checkMultipleCollision(rectangle, parent.getWallBounds())
                && PacmanUtils.checkCenteredInTile(sprite.getBoundingRectangle())
        ) {
            speedVector.set(targetSpeedVector);
        }
    }
    public void advance(float delta) {
        targetPositionVector.set(positionVector);
        targetPositionVector.add(speedVector.x * delta, speedVector.y * delta);
        Rectangle rectangle = new Rectangle(targetPositionVector.x, targetPositionVector.y, getBounds().width, getBounds().getHeight());
        if (!PacmanUtils.checkMultipleCollision(rectangle, parent.getWallBounds()))
            positionVector.set(targetPositionVector);
    }
    public Node getGridLocation(){
        return PacmanUtils.getGridLocation(sprite.getBoundingRectangle(), graph);
    }
    private void findTargetSpeed(){
        float deltaX, deltaY;
        deltaX = 0;
        deltaY = 0;
        if (scared || currentNode == null || nextNode == null){
            if (randomGenerator.nextBoolean())
                deltaX = 1;
            else
                deltaY = 1;
        } else {
            deltaX = nextNode.getX() - currentNode.getX();
            deltaY = nextNode.getY() - currentNode.getY();
        }
        if (deltaX == 1)
            currentAnimation = rightAnimation;
        if (deltaX == -1)
            currentAnimation = leftAnimation;
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
    public void reset(){
        disabled = true;
        softReset();
    }
    private void softReset() {
        animationStateTime = 0;
        currentAnimation = rightAnimation;
        positionVector.set(spawnPositionVector);
        sprite.setPosition(positionVector.x, positionVector.y);
        currentNode = getGridLocation();
    }
    public Rectangle getBounds(){
        return sprite.getBoundingRectangle();
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public void draw(Batch batch){
        sprite.draw(batch);
    }
}