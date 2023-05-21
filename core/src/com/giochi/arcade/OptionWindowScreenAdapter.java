package com.giochi.arcade;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giochi.arcade.SpaceInvaders.SpaceInvadersScreenGame;

public class OptionWindowScreenAdapter extends ScreenAdapter /** Classe della finestra di pausa. */
{

    private ScreenAdapter oldScreen;
    private Stage stage;

    private Viewport viewport;

    private Camera camera;

    private VerticalGroup group;

    private Button buttonResume , buttonExit;

    private static int WORLD_WIDTH = 640;

    private static int WORLD_HEIGHT = 480;


    public OptionWindowScreenAdapter(ScreenAdapter oldScreen)
    {
        this.oldScreen = oldScreen;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();

        viewport = new FitViewport(WORLD_WIDTH , WORLD_HEIGHT , camera);

        stage = new Stage();

        Gdx.input.setInputProcessor(stage); // rendo lo stage , e di conseguenza la finestra pronta a ricevere degli input.

        camera.update();

        group = new VerticalGroup();

        group.setFillParent(true); // diventa il "genitore" dei componenti a lui collegati

        buttonResume = new TextButton("Resume" , new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));

        buttonResume.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.graphics.setContinuousRendering(true);
                oldScreen.resume();
                ((Game) Gdx.app.getApplicationListener()).setScreen(oldScreen);
                return true;
            }
        });
        buttonExit = new TextButton("Exit" , new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));

        group.addActor(buttonResume);

        buttonExit.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                Gdx.app.exit();
                return true; // TMCH
            }
        });
        group.pack();

        group.addActor(buttonExit);

        stage.addActor(group);
    }

    @Override
    public void render(float delta)
    {

        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);

        stage.act(Math.min(delta, 1 / 30f));

        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {

        super.resize(width, height);

        viewport.update(width , height , true);
    }
}
