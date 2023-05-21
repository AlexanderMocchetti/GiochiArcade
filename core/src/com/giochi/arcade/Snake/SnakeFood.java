package com.giochi.arcade.Snake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giochi.arcade.controller.SnakeController;

public class SnakeFood
{

    private Viewport foodViewPort;


    private int x;
    private int y;

    private final Snake snake;

    private final SnakeController foodSnakeController;

    private boolean alive = false;

    public SnakeFood(Snake snake , SnakeController foodSnakeController)
    {
        this.snake = snake;

        this.foodSnakeController = foodSnakeController;
    }

    public void setFoodViewPort(Viewport foodViewPort) {
        this.foodViewPort = foodViewPort;
    }


    public void updatePosition ()
    {


        if (!alive)
        {
            boolean covered;
            do
            {
               covered = false;
               x = MathUtils.random( (int) foodViewPort.getWorldWidth() / snake.getSIZE() -1) * snake.getSIZE();
               y = MathUtils.random((int ) foodViewPort.getWorldHeight() / snake.getSIZE() -1) * snake.getSIZE();

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
            snake.createBodyPart(snake.getX(), snake.getY());
            foodSnakeController.increasedScore();
            alive = false;
        }
    }

    /**
     * @param foodShapeRenderer
     */
    public void draw(ShapeRenderer foodShapeRenderer)
    {
        foodShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        foodShapeRenderer.setColor(Color.RED);
        foodShapeRenderer.rect(x , y , snake.getSIZE() , snake.getSIZE());
        foodShapeRenderer.end();
    }

    public void reset ()
    {
        alive = false;
    }
}
