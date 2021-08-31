package ru.lomov.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import ru.lomov.game.DimStars;
import ru.lomov.game.base.BaseScreen;
import ru.lomov.game.math.Rect;
import ru.lomov.game.pool.BulletPool;
import ru.lomov.game.pool.EnemyPool;
import ru.lomov.game.sprite.Background;
import ru.lomov.game.sprite.Bullet;
import ru.lomov.game.sprite.EnemyShip;
import ru.lomov.game.sprite.MainShip;
import ru.lomov.game.sprite.Star;
import ru.lomov.game.utils.EnemyEmitter;

public class GameScreen extends BaseScreen {
    // private final Game game;

    private static final int STAR_COUNT = 64;

    private Background background;
    private Texture bg;

    //private EnemyShip enemyShip;

    private TextureAtlas atlas;
    private TextureAtlas atlasMain;


    private Star[] stars;
    private MainShip mainShip;
    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    //звуковое оформление
    private Sound shootSound;
    private Sound blastSound;

    private Music theme;

    private EnemyEmitter enemyEmitter;


    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/cosmos.jpg");
        background = new Background(bg);
        atlas = new TextureAtlas("textures/menuAtlas.pack");
        atlasMain = new TextureAtlas("textures/mainAtlas.pack");
        theme = Gdx.audio.newMusic(Gdx.files.internal("sounds/main_screen_music.mp3"));
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.mp3"));
        blastSound = Gdx.audio.newSound(Gdx.files.internal("sounds/blast.wav"));

        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        enemyPool = new EnemyPool(worldBounds, bulletPool);
        mainShip = new MainShip(atlasMain, bulletPool);
        enemyEmitter = new EnemyEmitter(worldBounds, blastSound, enemyPool, atlasMain);
        //enemyShip = new EnemyShip(worldBounds,bulletPool);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollision();
        freeAllDestroyed();
        draw();

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
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
        enemyPool.dispose();
        shootSound.dispose();
        theme.dispose();
        blastSound.dispose();

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
        mainShip.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        mainShip.update(delta);
        bulletPool.updateActiveSprites(delta);
        enemyPool.updateActiveSprites(delta);
        enemyEmitter.generate(delta);
        theme.play();
    }

    private void checkCollision() {
        List<EnemyShip> enemyShipList = enemyPool.getActiveSprites();

        for (EnemyShip enemyShip : enemyShipList) {
            if (enemyShip.isDestroyed()) {
                continue;
            }
            float minDist = enemyShip.getHalfWidth() + mainShip.getHalfHeight() - 0.07f;
            if (mainShip.pos.dst(enemyShip.pos) < minDist) {
                enemyShip.destroy();
                System.err.println("Enemy destroyed!");
            }
        }

        List<Bullet> bulletList = bulletPool.getActiveSprites();
        for (Bullet bullet : bulletList) {
            if (bullet.isDestroyed()) {
                continue;
            }
            for (EnemyShip enemyShip : enemyShipList) {
                if (enemyShip.isDestroyed()|| bullet.getOwner() != mainShip) {
                    continue;
                }
//                float bulletDist = bullet.getHalfWidth() + enemyShip.getHalfHeight();
//                if (enemyShip.pos.dst(bullet.pos) < bulletDist) {
//                    enemyShip.damage(bullet.getDamage());
//                    bullet.destroy();
//                    System.err.println("Enemy destroyed!");
//                }
                if (enemyShip.isBulletCollision(bullet)){
                    enemyShip.damage(bullet.getDamage());
                    bullet.destroy();
                    System.err.println("Enemy destroyed!");
                }
            }


//            if((mainShip.pos.x < enemyShip.pos.x + enemyShip.getWidth()) && (enemyShip.pos.x< mainShip.pos.x + mainShip.getWidth()) && (mainShip.pos.x < enemyShip.pos.x + enemyShip.getHeight()) && (enemyShip.pos.x< mainShip.pos.x + mainShip.getHeight())) {
//                enemyShip.destroy();
//                System.err.println("Enemy destroyed!");
//            }
        }

    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
        enemyPool.freeAllDestroyedActiveSprites();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        enemyPool.drawActiveSprites(batch);

        batch.end();
    }
}
