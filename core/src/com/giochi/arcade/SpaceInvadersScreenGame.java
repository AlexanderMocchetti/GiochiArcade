package com.giochi.arcade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SpaceInvadersScreenGame extends ScreenAdapter {

    private Camera camera;

    private Viewport viewport;

    private Stage stage;

    private SpriteBatch batch;

    private Texture imgPlayer;

    private Label labelScore;

    private Skin skin;

    private int score = 0;

    private Texture imgAlien;

    private int amountAliveAliens = 0;

    private Texture bulletImage;

    private Sound shoot;

    private PlayerSpaceInvaders player;

    private AlienSpaceInvaders [] aliens;

    private final int numWidthAliens = 5;

    private final int numHeightAliens = 5;

    private final int spacingAliens = 40;

    private int minXAliens;

    private int maxXAliens;

    private int minYAliens;

    private int maxYAliens;

    private Vector2 offsetAliens;

    private int directionAliens = 1;

    private float speedAliens = 100;

    private static final int WORLD_WIDTH = 640;

    private static final int WORLD_HEIGHT = 480;
    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        viewport = new FitViewport(600 , 600 , camera);
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("assets/skins/gdx-skins-master/gdx-skins-master/arcade/skin/arcade-ui.json"));
        ShapeRenderer renderer = new ShapeRenderer();
        offsetAliens = Vector2.Zero;
        batch = new SpriteBatch();
        labelScore = new Label("Killed: " + score , skin);
        labelScore.setColor(Color.WHITE);
        imgPlayer = new Texture("assets/images/space-invaders-ship.png");
        bulletImage = new Texture("assets/images/missile-space-invaders.png");
        imgAlien = new Texture("assets/images/enemy.png");
        shoot = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/shoot (1).ogg"));
        player = new PlayerSpaceInvaders(imgPlayer , bulletImage ,  Color.GREEN , shoot);
        aliens = new AlienSpaceInvaders[numWidthAliens * numHeightAliens];
        Table table = new Table();
        table.add(labelScore);
        table.setPosition(50 , 40);
        table.pack();
        stage.addActor(table);
        int i = 0;
        for (int y = 0 ; y < numHeightAliens ; y++) {
            for (int x = 0; x < numWidthAliens; x++) {
                Vector2 position = new Vector2(x * spacingAliens, y * spacingAliens);
                position.x += Gdx.graphics.getWidth() / 2;
                position.y += Gdx.graphics.getHeight();
                position.x -= (numWidthAliens / 2) * spacingAliens;
                position.y -= (numHeightAliens) * spacingAliens;
                aliens[i] = new AlienSpaceInvaders(position, imgAlien, Color.GREEN);
                i++;
            }
        }
    }

    @Override
    public void render(float delta) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        stage.act(Math.min(Gdx.graphics.getDeltaTime() , 1 / 30f));
        stage.draw();
        batch.begin();
        player.Draw(batch);
        boolean stop = false;
        for (int i = 0; i < aliens.length && !stop; i++)
        {
            if(aliens[i].isAlive())
            {
                if(player.getSpriteBullet().getBoundingRectangle().overlaps(aliens[i].getSpriteAlien().getBoundingRectangle()))
                {
                    player.getBulletPosition().y = 10000;
                    aliens[i].setAlive(false);
                    score++;
                    labelScore.setText("Killed: " + score);
                    stop = true;
                }
            }
        }

        minXAliens = 10000;
        minYAliens = 10000;
        maxXAliens = 0;
        maxYAliens = 0;
        amountAliveAliens = 0;

        for (int i = 0; i < aliens.length ; i++)
        {

            if (aliens[i].isAlive())
            {
                int indexX = i % numWidthAliens;

                int indexY = i / numWidthAliens;

                if (indexX > maxXAliens) maxXAliens = indexX;
                if (indexX < minXAliens) minXAliens = indexX;
                if (indexY > maxYAliens) maxYAliens = indexY;
                if (indexY < minYAliens) minYAliens = indexY;
                amountAliveAliens++;
            }

        }

        if (amountAliveAliens == 0)
        {
            for (int i = 0; i < aliens.length ; i++)
            {
                aliens[i].setAlive(true);
            }
            offsetAliens = new Vector2(0 , 0);
            batch.end();
            speedAliens = 100;
            return;
        }

        offsetAliens.x += directionAliens * deltaTime * speedAliens;
        if (aliens[maxXAliens].getPositionAlien().x >= Gdx.graphics.getWidth())
        {
            directionAliens = - 1;
            offsetAliens.y -= aliens[0].getSpriteAlien().getHeight() * aliens[0].getSpriteAlien().getScaleY() * 0.25f;
            speedAliens+=3;
        }

        if (aliens[minXAliens].getPositionAlien().x <= 0)
        {
            directionAliens = 1;

            offsetAliens.y -= aliens[0].getSpriteAlien().getHeight() * aliens[0].getSpriteAlien().getScaleY() * 0.25f;

            speedAliens+=3;
        }

        if (aliens[minYAliens].getPositionAlien().y <= 0)
        {
            // TODO: inserire la possibilità di rigiocare
            Gdx.app.exit();
        }


        for (int i = 0; i < aliens.length ; i++)
        {
            Vector2 tmp = new Vector2(aliens[i].getInitialPosition().x + offsetAliens.x , aliens[i].getInitialPosition().y + offsetAliens.y);
            aliens[i].setPositionAlien(tmp);
            if (aliens[i].isAlive())
            {
                aliens[i].Draw(batch);
            }
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height)
    {
        super.resize(width, height);
        camera.update();
    }
}
