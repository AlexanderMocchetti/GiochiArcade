package com.giochi.arcade.SpaceInvaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giochi.arcade.Files.SaveScore;
import com.giochi.arcade.Ui.OptionWindowScreenAdapter;

import java.io.*;


public class SpaceInvadersScreenGame extends ScreenAdapter implements SaveScore
{

    private static final int WORLD_WIDTH = 640;
    private static final int WORLD_HEIGHT = 480;

    private static final String GAME_OVER_TEXT = "GAME OVER! Press S to restart!";

    private static BitmapFont bitmap;
    private static final int numWidthAliens = 5;
    private static final int numHeightAliens = 5;
    private static final int spacingAliens = 40;

    private static GlyphLayout layout;


    private static Camera camera;
    private static Viewport viewport;
    private static Stage stage;
    private static SpriteBatch batch;
    private static Texture imgPlayer;
    private static Label labelScore;

    private static Button buttonPause;
    private static Texture imgAlien;

    private static SpaceInvadersSTATE state = SpaceInvadersSTATE.PLAYING;
    private static int amountAliveAliens = 0;
    private static Texture bulletImage;
    private static SpaceInvadersPlayer player;
    private static SpaceInvadersAlien[] aliens;
    private static int minXAliens;
    private static int maxXAliens;
    private static int minYAliens;
    private static int maxYAliens;
    private static Vector2 offsetAliens;
    private static int directionAliens = 1;
    private static float speedAliens = 100;

    @Override
    public void show()
    {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set((float) WORLD_WIDTH / 2 , (float) WORLD_HEIGHT / 2 , 0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        layout = new GlyphLayout();
        bitmap = new BitmapFont();
        offsetAliens = Vector2.Zero;
        batch = new SpriteBatch();
        imgPlayer = new Texture("images/space-invaders-ship.png");
        bulletImage = new Texture("images/missile-space-invaders.png");
        imgAlien = new Texture("images/enemy.png");
        Sound shoot = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot (1).ogg"));
        player = new SpaceInvadersPlayer(imgPlayer, bulletImage, Color.GREEN, shoot, viewport);
        aliens = new SpaceInvadersAlien[numWidthAliens * numHeightAliens];
        labelScore = new Label("Killed: " + player.getController().getScore(), new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));
        labelScore.setColor(Color.WHITE);
        buttonPause = new TextButton("Pause" , new Skin(Gdx.files.internal("gdx-skins-master/commodore64/skin/uiskin.json")));

        buttonPause.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hide();
                saveScore();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new OptionWindowScreenAdapter(getInstance()));
                return true;
            }
        });
        Table table = new Table();
        table.add(labelScore);
        table.add(buttonPause);
        table.setPosition(50, 40);
        table.pack();
        stage.addActor(table);
        createAliens();
    }

    private static void createAliens ()
    {

        int i = 0;
        for (int y = 0; y < numHeightAliens; y++)
        {
            for (int x = 0; x < numWidthAliens; x++)
            {
                Vector2 position = new Vector2(x * spacingAliens, y * spacingAliens);
                position.x += viewport.getWorldWidth() / 2;
                position.y += viewport.getWorldHeight();
                position.x -= ((float) numWidthAliens / 2) * spacingAliens;
                position.y -= (numHeightAliens) * spacingAliens;
                aliens[i] = new SpaceInvadersAlien(position, imgAlien, Color.GREEN , viewport);
                i++;
            }
        }
    }

    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
        batch.begin();
        player.draw(batch);
        batch.end();
        switch (state)
        {
            case PLAYING:
            {
                checkAlienDead();
                minXAliens = 5;
                minYAliens = 5;
                maxXAliens = 0;
                maxYAliens = 0;
                amountAliveAliens = 0;
                incrementAliens();
                resetAliens();
                checkBounds(delta);
                drawAliens();
            }
            break;
            case GAMEOVER:
            {
                gameOver();
                if (player.getController().checkForRestart())
                {
                    restart();
                }
            }
            break;
        }
    }


    public static void gameOver()
    {
        if (state == SpaceInvadersSTATE.GAMEOVER)
        {

            batch.begin();

            layout.setText(bitmap , GAME_OVER_TEXT);

            bitmap.draw(batch , GAME_OVER_TEXT ,  viewport.getWorldWidth() / 2 - layout.width / 2 , viewport.getWorldHeight() / 2 - layout.height / 2);

            batch.end();
        }
    }
    private static void restart ()
    {
        for (SpaceInvadersAlien alien : aliens)
        {
            alien.resetAlien();
        }
        state = SpaceInvadersSTATE.PLAYING;
        labelScore.setText("Killed: " + player.getController().getScore());
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void dispose()
    {
        stage.dispose();
        batch.dispose();
        imgPlayer.dispose();
        imgAlien.dispose();
    }

    private static void drawAliens()
    {
        for (SpaceInvadersAlien alien : aliens)
        {
            Vector2 tmp = new Vector2(alien.getInitialPosition().x + offsetAliens.x, alien.getInitialPosition().y + offsetAliens.y);
            alien.setPositionAlien(tmp);
            if (alien.isAlive())
            {
                batch.begin();
                alien.draw(batch);
                batch.end();
            }
        }
    }

    private static void checkBounds (float delta)
    {
        batch.begin();
        offsetAliens.x += directionAliens * delta * speedAliens;
        if (aliens[maxXAliens].getPositionAlien().x >= viewport.getWorldWidth())
        {
            directionAliens = -1;
            offsetAliens.y -= aliens[0].getSpriteAlien().getHeight() * aliens[0].getSpriteAlien().getScaleY() * 0.25f;
            speedAliens += 3;
        }

        if (aliens[minXAliens].getPositionAlien().x <= 0)
        {
            directionAliens = 1;

            offsetAliens.y -= aliens[0].getSpriteAlien().getHeight() * aliens[0].getSpriteAlien().getScaleY() * 0.25f;

            speedAliens += 3;
        }

        if (aliens[minYAliens].getPositionAlien().y <= 0)
        {
            state = SpaceInvadersSTATE.GAMEOVER;

        }
        batch.end();
    }

    private static void checkAlienDead()
    {
        batch.begin();
        boolean stop = false;
        for (int i = 0; i < aliens.length && !stop; i++)
        {
            if (aliens[i].isAlive())
            {
                if (player.getSpriteBullet().getBoundingRectangle().overlaps(aliens[i].getSpriteAlien().getBoundingRectangle()))
                {
                    player.getBulletPosition().y = 10000;
                    aliens[i].setAlive(false);
                    player.getController().increasedScore();
                    labelScore.setText("Killed: " + player.getController().getScore());
                    stop = true;
                }
            }
        }
        batch.end();
    }

    private static void incrementAliens()
    {
        batch.begin();
        for (int i = 0; i < aliens.length; i++)
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
        batch.end();
    }

    private static void resetAliens()
    {
        if (amountAliveAliens == 0)
        {
            batch.begin();
            for (SpaceInvadersAlien alien : aliens) {
                alien.setAlive(true);
            }
            offsetAliens = new Vector2(0, 0);
            batch.end();
            speedAliens = 100;
        }
    }

    public SpaceInvadersScreenGame getInstance()
    {
        return this;
    }

    @Override
    public void pause() {
       super.pause();
    }

    @Override
    public void saveScore() {

        try (FileWriter fileWriter = new FileWriter("SpaceInvadersScore.txt" , false)) {

            fileWriter.write(player.getController().getScore());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void saveMaxScore()
    {

    }

    @Override
    public void getScore()
    {

        try (BufferedReader reader = new BufferedReader(new FileReader("SpaceInvadersScore.txt")))
        {

            //player.getController().setScore(Integer.parseInt(reader.readLine()));

            labelScore.setText("Killed: " + player.getController().setScore(Integer.parseInt(reader.readLine())));

            System.out.println(player.getController().getScore());


        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
