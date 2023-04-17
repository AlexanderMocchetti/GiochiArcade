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
	}
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		for(Ball ball: balls){
			ball.update();
			ball.draw(shape);
		}
		shape.end();
	}
}
