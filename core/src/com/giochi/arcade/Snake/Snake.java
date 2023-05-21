package com.giochi.arcade.Snake;

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

    private final int STEP;

    private SnakeDIRECTIONS direction = SnakeDIRECTIONS.RIGHT;

    private final float MOVE_TIMER;

    private float timer;

    private SnakeSTATE snakeState = SnakeSTATE.PLAYING;

    private final Array<BodyPart> bodyParts;

    private Viewport snakeViewPort;

    public Snake(int size , int speed)
    {

        SIZE = 16 * size;

        STEP = SIZE;

        MOVE_TIMER = 1f / speed;

        timer = MOVE_TIMER;

        bodyParts = new Array<>();
    }

    private void move (SnakeDIRECTIONS direction)
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

    public SnakeSTATE update(float deltaTime)
    {
        timer -= deltaTime;

        if (timer <= 0)
        {
            move(direction);

            checkBounds();

            updateBodyParts();

            timer = MOVE_TIMER;

            checkBodyCollision();
        }
        return snakeState;
    }



    public void updateDirection (SnakeDIRECTIONS newDirection)
    {
        if (direction != newDirection)
        {
            switch (newDirection)
            {
                case RIGHT: {
                    updateIfNotOpposite(newDirection , SnakeDIRECTIONS.LEFT);
                }
                break;
                case LEFT: {
                    updateIfNotOpposite(newDirection , SnakeDIRECTIONS.RIGHT);
                }
                break;
                case UP:  {
                    updateIfNotOpposite(newDirection , SnakeDIRECTIONS.DOWN);
                }
                break;
                case DOWN: {
                    updateIfNotOpposite(newDirection , SnakeDIRECTIONS.UP);
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

        direction = SnakeDIRECTIONS.RIGHT;

        snakeState = SnakeSTATE.PLAYING;


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

    private void updateIfNotOpposite (SnakeDIRECTIONS newDirection , SnakeDIRECTIONS oppositeDirection)
    {
        if (direction != oppositeDirection || bodyParts.size == 0){
            direction = newDirection;
        }
    }

    public void draw(@NotNull ShapeRenderer snakeShapeRenderer)
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

    public void setSnakeViewPort(Viewport snakeViewPort) {
        this.snakeViewPort = snakeViewPort;
    }

    public void createBodyPart (int x , int y)
    {

       BodyPart newBodyPart = new BodyPart();
       newBodyPart.updateBodyPart(x , y);
       bodyParts.insert(0 , newBodyPart);
    }

    public void drawBodyParts (ShapeRenderer bodyPartShapeRenderer)
    {
        for (BodyPart bodyPart : bodyParts){
            bodyPart.draw(bodyPartShapeRenderer);
        }
    }

    public SnakeSTATE checkBodyCollision ()
    {

        for (BodyPart bodyPart : bodyParts)
        {
            if (bodyPart.x == x && bodyPart.y == y && bodyParts.size > 2) {
                snakeState = SnakeSTATE.GAME_OVER;
                break;
            }
        }
        return SnakeSTATE.PLAYING;
    }

    public void updateBodyParts () {

        if (bodyParts.size > 0) {
            BodyPart bodyPart = bodyParts.removeIndex(0);
            bodyPart.updateBodyPart(xBeforeMove , yBeforeMove);
            bodyParts.add(bodyPart);
        }
    }



    private class BodyPart
    {
        private int x;

        private int y;

        public BodyPart ()
        {

        }

        public void updateBodyPart(int x , int y)
        {
            this.x = x;
            this.y = y;
        }
        public void draw(ShapeRenderer bodyPartShapeRenderer)
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
