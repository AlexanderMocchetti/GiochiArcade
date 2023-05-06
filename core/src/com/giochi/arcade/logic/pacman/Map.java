package com.giochi.arcade.logic.pacman;

public class Map {
    public static final int WIDTH = 17;
    public static final int HEIGHT = 17;
    public static final boolean[][] edgeTable = {
            {false, true, false, true, false, true},
            {true, false, true, false, true, false},
            {true, },
            {false, true, false, true, true},
            {true},
            {true}
    };
    private Map(){

    }
}
