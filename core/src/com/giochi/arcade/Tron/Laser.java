package com.giochi.arcade.Tron;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Laser{
    private ArrayList<Vector2> laser;
    private Texture texture;

    public Laser(Texture texture){
        this.texture = texture;
        laser = new ArrayList<Vector2>();
    }

    public void addLaser(Vector2 lasers) {
        laser.add(lasers);
    }
    public void draw(SpriteBatch batch) {
        for (int i = 1; i < laser.size(); i++) {
            Vector2 p1 = laser.get(i - 1);
            Vector2 p2 = laser.get(i);
            batch.draw(texture, p1.x, p1.y, p2.dst(p1), 2);
        }
    }
}
