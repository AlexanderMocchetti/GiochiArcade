package com.giochi.arcade;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import jdk.internal.org.jline.terminal.MouseEvent;

public class StartScreen extends ScreenAdapter {

    private Camera camera;

    private Viewport viewport;

    private Batch batch;

    private TextButton startButton;

    private Button exitButton;

    private static int WORLD_WIDTH = 640;

    private static int WORLD_HEIGHT = 480;

    @Override
    public void show() {

        camera = new OrthographicCamera();

        viewport = new FitViewport(WORLD_WIDTH , WORLD_HEIGHT , camera);

        batch = new SpriteBatch();

        camera.update();

        Skin skin = new Skin();

        skin.s

        startButton = new TextButton("Start" ,);

        startButton.draw(batch);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
