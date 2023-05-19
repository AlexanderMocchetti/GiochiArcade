package com.giochi.arcade.Tron;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Laser{
    private Vector2 prevPos;
    private ArrayList<Vector2> laserPos;
    private Texture laserTexture;
    private Player owner;

    public Laser(Texture texture, Player owner){
        this.laserTexture = texture;
        laserPos = new ArrayList<>();
        this.owner = owner;
    }

    public void addPos(Vector2 position){
        laserPos.add(new Vector2(position));
        prevPos.set(position);
    }

    public void draw(SpriteBatch batch) {
        for (int i = 1; i < laserPos.size(); i++) {
            Vector2 p1 = laserPos.get(i - 1);
            Vector2 p2 = laserPos.get(i);
        }
    }

    public void update(float deltaTime){

    }

    public Texture getTexture() {
        return laserTexture;
    }

    public Player getOwner() {
        return owner;
    }
}
