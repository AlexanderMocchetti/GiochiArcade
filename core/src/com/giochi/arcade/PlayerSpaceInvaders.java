package com.giochi.arcade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class PlayerSpaceInvaders
{
    private final float speedPlayer = 300;
    private final float bulletSpeed = 1000;
    private Vector2 positionPlayer;
    private Vector2 bulletPosition;
    private Sprite spritePlayer;
    private Sprite spriteBullet;
    private Sound shoot;

    public PlayerSpaceInvaders(Texture img, Texture imgBullet, Color color, Sound shoot)
    {
        spritePlayer = new Sprite(img);
        spriteBullet = new Sprite(imgBullet);
        this.shoot = shoot;
        spritePlayer.setScale(1);
        spriteBullet.setScale(1);
        spritePlayer.setColor(color);
        spriteBullet.setColor(color);
        positionPlayer = new Vector2(Gdx.graphics.getWidth() / 2, spritePlayer.getScaleY() * spritePlayer.getHeight() / 2);
        bulletPosition = new Vector2(0, 10000);
    }


    public void update(float deltaTime)
    {
        if (Gdx.input.isButtonJustPressed(0) && bulletPosition.y >= Gdx.graphics.getHeight()) {
            bulletPosition.x = positionPlayer.x;
            bulletPosition.y = 0;
            shoot.play();


        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) positionPlayer.x -= deltaTime * speedPlayer;

        if (Gdx.input.isKeyPressed(Input.Keys.D)) positionPlayer.x += deltaTime * speedPlayer;


        if (positionPlayer.x - (spritePlayer.getWidth() * spritePlayer.getScaleX() / 2) <= 0)
            positionPlayer.x = (spritePlayer.getWidth() * spritePlayer.getScaleX() / 2);

        if (positionPlayer.x + (spritePlayer.getWidth() * spritePlayer.getScaleX() / 2) >= Gdx.graphics.getWidth())
            positionPlayer.x = Gdx.graphics.getWidth() - (spritePlayer.getWidth() * spritePlayer.getScaleX() / 2);


        bulletPosition.y += deltaTime * bulletSpeed;

    }

    public void draw(SpriteBatch batch) {
        update(Gdx.graphics.getDeltaTime());
        spritePlayer.setPosition(positionPlayer.x, positionPlayer.y);
        spritePlayer.draw(batch);
        spriteBullet.setPosition(bulletPosition.x, bulletPosition.y);
        spriteBullet.draw(batch);
    }


    public Sprite getSpriteBullet() {
        return spriteBullet;
    }


    public Vector2 getBulletPosition() {
        return bulletPosition;
    }

}
