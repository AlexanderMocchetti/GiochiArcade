package com.giochi.arcade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.jetbrains.annotations.NotNull;

public class Snake {

    private int x;

    private int y;

    private final int SIZE;

    private int bodySize = 0;

    private final int STEP;

    private DIRECTIONS direction = DIRECTIONS.RIGHT;

    private final float MOVE_TIMER;

    private float timer;

    public Snake(int size , int speed)
    {

        SIZE = 16 * size;

        STEP = SIZE;

        MOVE_TIMER = 1f / speed;

        timer = MOVE_TIMER;
    }

    private void move (DIRECTIONS direction)
    {

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
                return;
            }

        }

    }

    public void Update (float deltaTime)
    {
        timer -= deltaTime;

        if (timer <= 0)
        {
            move(direction);

            checkBounds();

            timer = MOVE_TIMER;
        }
    }



    public void updateDirection (DIRECTIONS newDirection)
    {
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

    private void checkBounds ()
    {
        if (x > Gdx.graphics.getWidth())
            x = SIZE / 2;
        if (x < 0)
            x = Gdx.graphics.getWidth();
        if (y > Gdx.graphics.getHeight())
            y = 0;
        if (y < 0)
            y = Gdx.graphics.getHeight();
    }

    private void updateIfNotOpposite (DIRECTIONS newDirection , DIRECTIONS oppositeDirection)
    {
        if (direction != oppositeDirection || bodySize == 0){
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

    public BodyPart createBodyPart (int x , int y)
    {
        return new BodyPart();
    }

    public void updateBodyPart (BodyPart bodyPart , int x , int y)
    {
        bodyPart.UpdateBodyPart(x , y);
    }

    public void drawBodyPart(BodyPart bodyPart , ShapeRenderer bodyPartShapeRenderer)
    {
        bodyPart.Draw(bodyPartShapeRenderer);
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
                bodyPartShapeRenderer.end();
            }
        }

    }
}
