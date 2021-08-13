package ru.lomov.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.DimStars;
import ru.lomov.game.base.BaseScreen;
import ru.lomov.game.math.Rect;
import ru.lomov.game.pool.BulletPool;
import ru.lomov.game.sprite.Background;
import ru.lomov.game.sprite.MainShip;
import ru.lomov.game.sprite.Star;

public class GameScreen extends BaseScreen {
   // private final Game game;

    private static final int STAR_COUNT = 64;

    private Background background;
    private Texture bg;

    private TextureAtlas atlas;
    private TextureAtlas atlasMain;


    private Star[] stars;
    private MainShip mainShip;
    private BulletPool bulletPool;



    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/cosmos.jpg");
        background = new Background(bg);
        atlas = new TextureAtlas("textures/menuAtlas.pack");
        atlasMain = new TextureAtlas("textures/mainAtlas.pack");

        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        mainShip = new MainShip(atlasMain, bulletPool);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star: stars) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);

    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        atlasMain.dispose();
        bulletPool.dispose();

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        mainShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        mainShip.touchUp(touch, pointer,button);
        return false;
    }

    private void update(float delta){
        for (Star star: stars) {
            star.update(delta);
        }
        mainShip.update(delta);
        bulletPool.updateActiveSprites(delta);
    }
    private void freeAllDestroyed(){
        bulletPool.freeAllDestroyedActiveSprites();
    }
    private void  draw(){
        batch.begin();
       //batch.enableBlending();
        background.draw(batch);
        for (Star star: stars) {
            star.draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        batch.end();
    }
}
