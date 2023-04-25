package com.giochi.arcade;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.jetbrains.annotations.NotNull;

public class Snake {

    private int x;

    private int y;

    private final int SIZE;

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

            timer = MOVE_TIMER;
        }
    }

    public void updateDirection (DIRECTIONS direction)
    {
        this.direction = direction;
    }

    public void Draw (Batch snakeBatch , @NotNull ShapeRenderer snakeShapeRenderer)
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
}
