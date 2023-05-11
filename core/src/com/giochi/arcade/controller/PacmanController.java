package com.giochi.arcade.controller;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.giochi.arcade.logic.pacman.Player;

public class PacmanController extends InputAdapter {
    private final Player player;
    private final Vector2 UP, DOWN, LEFT, RIGHT;
    public PacmanController(Player player) {
        this.player = player;
        UP = new Vector2(0, player.getSpeed());
        DOWN = new Vector2(0, -player.getSpeed());
        LEFT = new Vector2(-player.getSpeed(), 0);
        RIGHT = new Vector2(player.getSpeed(), 0);
    }
    @Override
    public boolean keyDown(int keycode) {
        Vector2 speed;
        switch(keycode){
            case 19:
            case 51:
                speed = UP;
                break;
            case 20:
            case 47:
                speed = DOWN;
                break;
            case 21:
            case 29:
                speed = LEFT;
                break;
            case 22:
            case 32:
                speed = RIGHT;
                break;
            default:
                return false;
        }
        player.setTargetSpeedVector(speed);
        return true;
    }
}
