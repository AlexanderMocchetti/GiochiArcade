package com.giochi.arcade.Ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Screen of the start window.
 * @author Thomas Riotto.
 *
 */


public class StartScreen extends ScreenAdapter
{ /** finestra iniziale (start window) */

    private Stage stage;

    private Camera camera;

    private Viewport viewport;

    private VerticalGroup group;

    private TextButton startButton;

    private TextButton exitButton;

    private static int WORLD_WIDTH = 640;

    private static int WORLD_HEIGHT = 480;



    @Override
    public void show() {



        camera = new OrthographicCamera();

        viewport = new FitViewport(WORLD_WIDTH , WORLD_HEIGHT , camera);

        stage = new Stage(viewport);

        Gdx.input.setInputProcessor(stage); // rendo lo stage , e di conseguenza la finestra pronta a ricevere degli input.

        camera.update();

        group = new VerticalGroup();

        group.setFillParent(true); // diventa il "genitore" dei componenti a lui collegati



        startButton = new TextButton("Start" , new Skin(Gdx.files.internal("gdx-skins-master/glassy/skin/glassy-ui.json")));

        startButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new WindowScreenGame());
                dispose();
            }

        });

        group.addActor(startButton);

        exitButton = new TextButton("Exit" , new Skin(Gdx.files.internal("gdx-skins-master/glassy/skin/glassy-ui.json")));

        exitButton.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });



        group.pack();

        group.addActor(exitButton);

        stage.addActor(group);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
       viewport.update(width , height);
    }
}
