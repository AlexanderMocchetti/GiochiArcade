package com.giochi.arcade.Tron;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.IndexArray;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;

public class Player{
    private Sprite sprite;
    private Vector2 position;
    private Vector2 direction;
    private float speed;
    private float rotation = 0;
    private ArrayList<Vector2> laserPositions = new ArrayList<>(200);
    public static final float bikeWidth = 53/5f, bikeHeight = 26/5f;

    public Player(Texture img, Vector2 position, Vector2 direction, float speed){
        sprite = new Sprite(img);
        this.position = position;
        this.direction = direction;
        this.speed = speed;
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

    public void drawLaser(ShapeRenderer shape) {
        for(Vector2 laserPosition : laserPositions){
          shape.rect(laserPosition.x, laserPosition.y, 1, 1);
        }
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

    public void setSpeed(float speed){
        this.speed = speed;
    }
    public void reset(){
        laserPositions.clear();
    }
    public ArrayList<Vector2> getLaserPositions(){
        return laserPositions;
    }

    public void setLaserPositions(ArrayList<Vector2> laserPositions){
        this.laserPositions = laserPositions;
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public Texture getTexture(){
        return sprite.getTexture();
    }

    public Vector2 getPosition(){
        return position;
    }

    public void setImg(Texture img){
        sprite.setTexture(img);
    }

    public void setPosition(Vector2 position){
        this.position = position;
    }

    public boolean checkCollisionWithEnemyLaser(Player enemyPlayer){
        ArrayList<Vector2> enemyLaserPositions = enemyPlayer.laserPositions;        //posizioni laser nemico
        for(Vector2 enemyLaserPosition : enemyLaserPositions){
            Rectangle laser = new Rectangle(enemyLaserPosition.x, enemyLaserPosition.y, 1, 1 );
            if(sprite.getBoundingRectangle().overlaps(laser)) {
                return true;
            }
        }
        return false;
    }
}
