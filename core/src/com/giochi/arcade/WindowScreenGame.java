package com.giochi.arcade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;
import java.util.Vector;

public class WindowScreenGame extends ScreenAdapter
{

    private Stage stage;

    private Camera camera;

    private Viewport viewport;

    private Table table;

    private static int WORLD_WIDTH = 640;

    private static int WORLD_HEIGHT = 480;



    @Override
    public void show()
    {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();

        viewport = new FitViewport(WORLD_WIDTH , WORLD_HEIGHT , camera);

        camera.update();

        table = new Table();

        table.setFillParent(true);
    }
    @Override
    public void render(float delta)
    {

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
       viewport.update(width , height , true);
    }
}
