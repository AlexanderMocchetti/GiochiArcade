package com.giochi.arcade.Pong.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giochi.arcade.OptionWindowScreenAdapter;

public class PongScreen extends ScreenAdapter {

    public static final float
            WORLD_WIDTH = 640,
            WORLD_HEIGHT = 480;

    private Stage stage;


    private Button buttonPause;

    private  Batch batch;
    private ShapeRenderer shape;
    private Camera camera;
    private Viewport viewport;
    private final Ball ball;
    private final Player p1, p2;
    private boolean start;
    private BitmapFont font;

    public PongScreen() {
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        p1 = new Player(20, WORLD_HEIGHT / 2, 5, 30, 3, 1);
        p2 = new Player(WORLD_WIDTH - 20, WORLD_HEIGHT / 2, 5, 30, 3, 2);
        ball = new Ball(WORLD_HEIGHT / 2, WORLD_WIDTH / 2, 5F, 3, 1.5f, p1, p2);
        start = false;

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH , WORLD_HEIGHT , camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        buttonPause = new TextButton("Pause" , new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));
        buttonPause.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pause();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new OptionWindowScreenAdapter(getInstance()));
                return true;
            }
        });
        Table table = new Table();
        table.add(buttonPause);
        table.setPosition(40, 10);
        table.pack();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(0.5f);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        /*if (!start) {
            batch.begin();
            font.draw(batch, "Premere spazio per avviare il gioco", WORLD_WIDTH / 5, WORLD_HEIGHT / 5);
            batch.end();
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                start = true;
            } else {
                return;
            }
        }

         */
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
        //Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.update();
        ball.draw(shape);
        p1.update();
        p2.update();
        p1.draw(shape);
        p2.draw(shape);
        shape.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
        shape.setProjectionMatrix(camera.combined);
    }

    public PongScreen getInstance ()
    {
        return this;
    }
}