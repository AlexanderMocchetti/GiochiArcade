package com.giochi.arcade.Tron;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class TronPlayer {
    private Sprite sprite;
    private Vector2 position;
    private Vector2 direction;
    private float speed;
    private float rotation = 0;
    private ArrayList<Vector2> laserPositions = new ArrayList<>(200);
    public static final float bikeWidth = 53/5f, bikeHeight = 26/5f;

    public TronPlayer(Texture img, Vector2 position, Vector2 direction, float speed){
        sprite = new Sprite(img);
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        sprite.setSize(40 , 40);
        sprite.setBounds(position.x, position.y, bikeWidth, bikeHeight);
        sprite.setOrigin(bikeWidth/2, bikeHeight/2);
    }

    public void move(float delta){
        laserPositions.add(new Vector2(position.x + 5, position.y -0.01f));
        position.x += direction.x * speed * delta;
        position.y += direction.y * speed * delta;
        sprite.setPosition(position.x, position.y);
        sprite.setRotation(rotation);
    }

    public void reset(){
        laserPositions.clear();
    }
    public void drawLaser(ShapeRenderer shape) {
        for(Vector2 laserPosition : laserPositions){
          shape.rect(laserPosition.x, laserPosition.y, 1, 1);
        }
        //shape.rect(laserPositions.get(laserPositions.size() - 1).x , laserPositions.get(laserPositions.size() - 1).y , 1 , 1 );
    }

    public void setDirection(float x, float y){
        if(direction.x != -x || direction.y != -y){
            direction.set(x, y);
        }
    }

    public void setRotation(float rotation){
        if(Math.abs(this.rotation - rotation)!= 180){           //Math.abs valore assoluto != 180 (direziona opposta) set nuova rotazione
            this.rotation = rotation;
        }
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public boolean checkCollisionWithEnemyLaser(TronPlayer enemyTronPlayer){
        ArrayList<Vector2> enemyLaserPositions = enemyTronPlayer.laserPositions;        //posizioni laser nemico
        for(Vector2 enemyLaserPosition : enemyLaserPositions){
            Rectangle laser = new Rectangle(enemyLaserPosition.x, enemyLaserPosition.y, 1, 1 );
            if(sprite.getBoundingRectangle().overlaps(laser)) {
                return true;
            }
        }
        return false;
    }
}
