package com.giochi.arcade;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Random;

public class Application extends ApplicationAdapter {
	ShapeRenderer shape;
	Random random = new Random();
	ArrayList<Ball> balls = new ArrayList<>(10);
	Ball ball;
	Paddle paddle;
	@Override
	public void create () {
		shape = new ShapeRenderer();
		for (int i = 0; i < 10; i++) {
			balls.add(new Ball(
					random.nextInt(Gdx.graphics.getWidth()),
					random.nextInt(Gdx.graphics.getHeight()),
					random.nextInt(100),
					random.nextInt(15),
					random.nextInt(15)
			));
		}
		ball = balls.get(0);
		paddle = new Paddle(5, 10, Gdx.graphics.getWidth() / 2, 50);
	}
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		ball.update();
		paddle.update();
		paddle.draw(shape);
		ball.draw(shape);
		shape.end();
	}
}
