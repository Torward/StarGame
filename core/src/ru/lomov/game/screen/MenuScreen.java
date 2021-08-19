package ru.lomov.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.base.BaseScreen;
import ru.lomov.game.math.Rect;
import ru.lomov.game.sprite.Background;
import ru.lomov.game.sprite.ExitButton;
import ru.lomov.game.sprite.PlayButton;
import ru.lomov.game.sprite.Star;

public class MenuScreen extends BaseScreen {

    private final Game game;
    private static final int STAR_COUNT = 220;
    private Texture bg;
    private Texture cckpt;
    private Background background;
    private Background cockpit;


    private TextureAtlas atlas;
    private Star[] stars;
    private ExitButton exitButton;
    private PlayButton playButton;
    private Sound startSound;
    private Music theme;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures/menuAtlas.pack");
        bg = new Texture("textures/cosmos.jpg");
        cckpt = new Texture("textures/cockpit.png");
        startSound = Gdx.audio.newSound(Gdx.files.internal("sounds/start_btn.wav"));
        theme = Gdx.audio.newMusic(Gdx.files.internal("sounds/title-screen.mp3"));
        cockpit = new Background(cckpt);
        background = new Background(bg);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        exitButton = new ExitButton(atlas);
        playButton = new PlayButton(atlas, game);

    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        cockpit.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        exitButton.resize(worldBounds);
        playButton.resize(worldBounds);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();



    }

    @Override
    public void dispose() {
        super.dispose();
        cckpt.dispose();
        bg.dispose();
        atlas.dispose();
        startSound.dispose();
        theme.dispose();

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        exitButton.touchDown(touch, pointer, button);
        playButton.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        exitButton.touchUp(touch, pointer, button);
        playButton.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        theme.play();

    }

    private void draw() {

        //mainObject.draw(batch);
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        batch.enableBlending();
        cockpit.draw(batch);
        exitButton.draw(batch);
        playButton.draw(batch);
        batch.end();
    }
}