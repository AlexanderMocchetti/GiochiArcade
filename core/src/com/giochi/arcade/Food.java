package com.giochi.arcade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Food
{
    private int x;
    private int y;
    private Snake snake;

    private boolean alive = false;

    public Food (Snake snake)
    {
        this.snake = snake;
    }

    public void updatePosition ()
    {


        if (!alive)
        {
            boolean covered;
            do
            {
               covered = false;
               x = MathUtils.random(((Gdx.graphics.getWidth() / snake.getSIZE()) -1)) * snake.getSIZE();
               y = MathUtils.random(((Gdx.graphics.getHeight() / snake.getSIZE()) - 1))* snake.getSIZE();

               if (x == snake.getX() || y == snake.getY())
               {
                   covered = true;
               }
               alive = true;
            }
            while (covered);
        }
    }

    public void checkFoodCollision ()
    {
        if (alive && x == snake.getX() && y == snake.getY())
        {
            alive = false;
        }
    }
    public void Draw (ShapeRenderer foodShapeRenderer)
    {
        foodShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        foodShapeRenderer.setColor(Color.RED);
        foodShapeRenderer.rect(x , y , snake.getSIZE() , snake.getSIZE());
        foodShapeRenderer.end();
    }
}
