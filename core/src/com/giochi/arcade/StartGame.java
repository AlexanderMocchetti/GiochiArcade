package com.giochi.arcade;

import com.badlogic.gdx.Game;

/**
 * Class of the start window.
 *
 * @author Thomas Riotto
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
