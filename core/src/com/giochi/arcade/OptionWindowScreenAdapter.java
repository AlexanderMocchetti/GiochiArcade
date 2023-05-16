package com.giochi.arcade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AddListenerAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class OptionWindowScreenAdapter extends ScreenAdapter
{
    private Stage stage;

    private Viewport viewport;

    private Camera camera;

    private VerticalGroup group;

    private Button buttonResume , buttonExit;

    private static int WORLD_WIDTH = 640;

    private static int WORLD_HEIGHT = 480;



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

        //buttonResume.addAction();

        buttonExit = new TextButton("Exit" , new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));


    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewport.update(width , height , true);
    }
}
