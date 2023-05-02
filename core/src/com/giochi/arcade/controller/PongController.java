package com.giochi.arcade.controller;

import com.badlogic.gdx.InputAdapter;
import com.giochi.arcade.logic.pong.Player;

public class PongController extends InputAdapter {
    private final Player player;

    public PongController(Player player) {
        this.player = player;
    }
}
