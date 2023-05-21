package com.giochi.arcade.Ui;

import com.badlogic.gdx.Game;

/**
 * Class of the start window.
 *
 * @author Thomas Riotto
 *
 *
 *
 */
public class StartGame extends Game
{

    @Override
    public void create()
    {
        setScreen(new StartScreen());
    }
}
