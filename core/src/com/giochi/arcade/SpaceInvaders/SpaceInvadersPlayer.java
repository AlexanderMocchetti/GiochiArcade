package com.giochi.arcade.SpaceInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giochi.arcade.Controller.SpaceInvadersController;


public class SpaceInvadersPlayer
{
    private final float speedPlayer = 300;
    private final float bulletSpeed = 1000;

    private Viewport spaceInvadersPlayerViewPort;

    private SpaceInvadersController controller;
    private Vector2 positionPlayer;
    private Vector2 bulletPosition;
    private Sprite spritePlayer;
    private Sprite spriteBullet;
    private Sound shoot;

    public SpaceInvadersPlayer(Texture img, Texture imgBullet, Color color, Sound shoot , Viewport spaceInvadersPlayerViewPort)
    {
        spritePlayer = new Sprite(img);
        spriteBullet = new Sprite(imgBullet);
        controller = new SpaceInvadersController();
        this.shoot = shoot;
        this.spaceInvadersPlayerViewPort = spaceInvadersPlayerViewPort;
        spritePlayer.setScale(1);
        spritePlayer.setSize(25 , 25);
        spriteBullet.setScale(1);
        spriteBullet.setSize(20 , 20);
        spritePlayer.setColor(color);
        spriteBullet.setColor(color);
        //positionPlayer = new Vector2((float) Gdx.graphics.getWidth() / 2, spritePlayer.getScaleY() * spritePlayer.getHeight() / 2);
        positionPlayer = new Vector2(spaceInvadersPlayerViewPort.getWorldWidth() / 2 , spritePlayer.getScaleY() * spritePlayer.getHeight() / 2);
        bulletPosition = new Vector2(0, 10000);
    }


    public void update(float deltaTime)
    {
        if (controller.checkForShoot() && bulletPosition.y >= Gdx.graphics.getHeight()) {

            bulletPosition.x = positionPlayer.x;
            bulletPosition.y = 0;
            shoot.play();


        }

        SpaceInvadersDIRECTIONS direction = controller.queryInputSpaceInvadersDirections(); /** d */


        if (direction == SpaceInvadersDIRECTIONS.LEFT) positionPlayer.x -= deltaTime * speedPlayer;

        if (direction == SpaceInvadersDIRECTIONS.RIGHT) positionPlayer.x += deltaTime * speedPlayer;


        if (positionPlayer.x - (spritePlayer.getWidth() * spritePlayer.getScaleX() / 2) <= 0)
            positionPlayer.x = (spritePlayer.getWidth() * spritePlayer.getScaleX() / 2);

        if (positionPlayer.x + (spritePlayer.getWidth() * spritePlayer.getScaleX() / 2) >= Gdx.graphics.getWidth())
            //positionPlayer.x = Gdx.graphics.getWidth() - (spritePlayer.getWidth() * spritePlayer.getScaleX() / 2);
            positionPlayer.x = spaceInvadersPlayerViewPort.getWorldWidth() - (spritePlayer.getWidth() * spritePlayer.getScaleX() / 2);

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

    public SpaceInvadersController getController() {
        return controller;
    }
}
