package com.giochi.arcade.Controller;

import com.badlogic.gdx.InputAdapter;
import com.giochi.arcade.Pong.Player;

public class PongController extends InputAdapter {
    private final Player player;

    public PongController(Player player) {
        this.player = player;
    }
}
