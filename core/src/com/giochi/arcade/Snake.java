package com.giochi.arcade;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.jetbrains.annotations.NotNull;

public class Snake {

    private int x;

    private int y;

    private int xBeforeMove;

    private int yBeforeMove;

    private final int SIZE;

    private final int bodySize = 0;

    private final int STEP;

    private DIRECTIONS direction = DIRECTIONS.RIGHT;

    private final float MOVE_TIMER;

    private float timer;

    private STATE state = STATE.PLAYING;

    //private boolean directionSet = false;

    private final Array<BodyPart> bodyParts;

    private Viewport snakeViewPort;

    private Camera snakeCamera;

    public Snake(int size , int speed)
    {

        SIZE = 16 * size;

        STEP = SIZE;

        MOVE_TIMER = 1f / speed;

        timer = MOVE_TIMER;

        bodyParts = new Array<>();
    }

    private void move (DIRECTIONS direction)
    {

        xBeforeMove = x;
        yBeforeMove = y;

        switch (direction) {
            case RIGHT:{
                x+=STEP;
                return;
            }
            case LEFT:{
                x-=STEP;
                return;
            }
            case UP:{
                y+=STEP;
                return;
            }
            case DOWN:{
                y-=STEP;
            }

        }

    }

    public STATE Update (float deltaTime)
    {
        timer -= deltaTime;

        if (timer <= 0)
        {
            move(direction);

            checkBounds();

            updateBodyParts();

            timer = MOVE_TIMER;

            checkBodyCollision();

            //directionSet = false;
        }
        return state;
    }



    public void updateDirection (DIRECTIONS newDirection)
    {
        if (direction != newDirection)
        {
            //directionSet = true;
            switch (newDirection)
            {
                case RIGHT: {
                    updateIfNotOpposite(newDirection , DIRECTIONS.LEFT);
                }
                break;
                case LEFT: {
                    updateIfNotOpposite(newDirection , DIRECTIONS.RIGHT);
                }
                break;
                case UP:  {
                    updateIfNotOpposite(newDirection , DIRECTIONS.DOWN);
                }
                break;
                case DOWN: {
                    updateIfNotOpposite(newDirection , DIRECTIONS.UP);
                }
                break;

            }
        }

    }

    public void reset ()
    {

        x = 0;

        y = 0;

        xBeforeMove = 0;

        yBeforeMove = 0;

        bodyParts.clear();

        timer = MOVE_TIMER;

        direction = DIRECTIONS.RIGHT;

        state = STATE.PLAYING;


    }

    private void checkBounds ()
    {
        if (x >= snakeViewPort.getWorldWidth())
            x = 0;

        if (x < 0)
            x = Math.round(snakeViewPort.getWorldWidth()) - STEP;

        if (y >= snakeViewPort.getWorldHeight())
            y = 0;

        if (y < 0)
            y = Math.round(snakeViewPort.getWorldHeight()) - STEP;
    }

    private void updateIfNotOpposite (DIRECTIONS newDirection , DIRECTIONS oppositeDirection)
    {
        if (direction != oppositeDirection || bodyParts.size == 0){
            direction = newDirection;
        }
    }

    public void Draw (@NotNull ShapeRenderer snakeShapeRenderer)
    {
        drawHead(snakeShapeRenderer);
    }

    private void drawHead (@NotNull ShapeRenderer snakeShapeRenderer)
    {
        snakeShapeRenderer.begin(ShapeRenderer.ShapeType.Filled); // specify that it will draw filled squares
        snakeShapeRenderer.setColor(Color.GREEN);
        snakeShapeRenderer.rect(x , y , SIZE , SIZE);// create a rectangle
        snakeShapeRenderer.end();

    }

    public DIRECTIONS getDirection() {
        return direction;
    }

    public void setDirection(DIRECTIONS direction) {
        this.direction = direction;
    }

    public int getSIZE() {
        return SIZE;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Viewport getSnakeViewPort() {
        return snakeViewPort;
    }

    public void setSnakeViewPort(Viewport snakeViewPort) {
        this.snakeViewPort = snakeViewPort;
    }

    public Camera getSnakeCamera() {
        return snakeCamera;
    }

    public void setSnakeCamera(Camera snakeCamera) {
        this.snakeCamera = snakeCamera;
    }

    public void createBodyPart (int x , int y)
    {

       BodyPart newBodyPart = new BodyPart();
       newBodyPart.UpdateBodyPart(x , y);
       bodyParts.insert(0 , newBodyPart);
    }

    public void drawBodyParts (ShapeRenderer bodyPartShapeRenderer)
    {
        for (BodyPart bodyPart : bodyParts){
            bodyPart.Draw(bodyPartShapeRenderer);
        }
    }

    public STATE checkBodyCollision ()
    {

        for (BodyPart bodyPart : bodyParts)
        {
            if (bodyPart.x == x && bodyPart.y == y && bodyParts.size > 2) {
                state = STATE.GAME_OVER;
                break;
            }
        }
        return STATE.PLAYING;
    }

    public void updateBodyParts () {

        if (bodyParts.size > 0) {
            BodyPart bodyPart = bodyParts.removeIndex(0);
            bodyPart.UpdateBodyPart(xBeforeMove , yBeforeMove);
            bodyParts.add(bodyPart);
        }
    }



    private class BodyPart
    {
        private int x;

        private int y;

        BodyPart ()
        {

        }

        public void UpdateBodyPart (int x , int y)
        {
            this.x = x;
            this.y = y;
        }
        public void Draw (ShapeRenderer bodyPartShapeRenderer)
        {
            if (!(this.x == Snake.this.x && this.y == Snake.this.y))
            {
                bodyPartShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                bodyPartShapeRenderer.setColor(Color.GREEN);
                bodyPartShapeRenderer.rect(x , y , getSIZE() , getSIZE());
                bodyPartShapeRenderer.end();
            }
        }

    }
}
