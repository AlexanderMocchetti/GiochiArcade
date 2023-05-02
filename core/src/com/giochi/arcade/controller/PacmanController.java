package com.giochi.arcade.controller;

import com.badlogic.gdx.InputAdapter;
import com.giochi.arcade.logic.pacman.Direction;
import com.giochi.arcade.logic.pacman.Player;

public class PacmanController extends InputAdapter {
    private final Player player;
    public PacmanController(Player player) {
        this.player = player;
    }
    @Override
    public boolean keyDown(int keycode) {
        Direction direction;
        switch(keycode){
            case 19:
            case 51:
                direction = Direction.UP;
                break;
            case 20:
            case 47:
                direction = Direction.DOWN;
                break;
            case 21:
            case 29:
                direction = Direction.LEFT;
                break;
            case 22:
            case 32:
                direction = Direction.RIGHT;
                break;
            default:
                return false;
        }
        player.setDirection(direction);
        return true;
    }
}
