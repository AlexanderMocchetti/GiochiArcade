package com.giochi.arcade.logic.pacman;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {
    private final TextureAtlas ghostAtlas;
    private final TextureAtlas playerAtlas;
    private final TiledMap map;

    public TextureAtlas getGhostAtlas() {
        return ghostAtlas;
    }

    public TextureAtlas getPlayerAtlas() {
        return playerAtlas;
    }

    public TiledMap getMap() {
        return map;
    }

    public Assets() {
        ghostAtlas = new TextureAtlas("ghost.atlas");
        playerAtlas = new TextureAtlas("pacman.atlas");
        map = new TmxMapLoader().load("PacmanMap.tmx");
    }

    @Override
    public void dispose(){
        ghostAtlas.dispose();
        playerAtlas.dispose();
        map.dispose();
    }
}
