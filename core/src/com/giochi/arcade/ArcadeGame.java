package com.giochi.arcade;

import com.badlogic.gdx.Game;
import com.giochi.arcade.ui.LoadingScreen;
import com.giochi.arcade.ui.MenuScreen;
import com.giochi.arcade.ui.Screens;

public class ArcadeGame extends Game{
    LoadingScreen loadingScreen;
    MenuScreen menuScreen;
    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        changeScreens(Screens.LOADING);

    }
    public void changeScreens(Screens screen){
        switch(screen){
            case LOADING:
                setScreen(loadingScreen);
                break;
            case MENU:
                if(menuScreen == null)
                    menuScreen = new MenuScreen(this);
                setScreen(menuScreen);
                break;
        }
    }
}