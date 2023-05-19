package com.giochi.arcade;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher
{
	/**
	 * start point of the program.
	 * It set the configuration of the application.
	 *
	 */
	public static void main (String[] arg)
	{
		/**
		 * create the cofiguration of the app
		 *  */
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60); /** set the fps  */
		config.setTitle("GiochiArcade");
		new Lwjgl3Application(new StartGame() , config); /** create a new Application  */
	}
}
