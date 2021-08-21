package ru.lomov.game.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.math.Rect;
import ru.lomov.game.math.Rnd;
import ru.lomov.game.pool.EnemyPool;
import ru.lomov.game.sprite.EnemyShip;

public class EnemyEmitter {
    private static final float GENERATE_INTERVAL = 4f;

    private static final float ENEMY_SMALL_HEIGHT = 0.08f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.035f;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 1f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final int ENEMY_SMALL_HP = 1;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.1f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.04f;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 1.5f;
    private static final int ENEMY_MEDIUM_BULLET_DAMAGE = 5;
    private static final int ENEMY_MEDIUM_HP = 5;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.05f;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 2f;
    private static final int ENEMY_BIG_BULLET_DAMAGE = 10;
    private static final int ENEMY_BIG_HP = 10;


    private final Rect worldBounds;
    private final Sound shootSound;

    private final TextureRegion[] enemySmallRegions;
    private final TextureRegion[] enemyMediumRegions;
    private final TextureRegion[] enemyBigRegions;

    private final Vector2 enemySmallVel = new Vector2(0f, -0.2f);
    private final Vector2 enemySmallVelY = new Vector2(0, 0);
    private final Vector2 enemySmallBulletVelY = new Vector2(0, -1.0f);

    private final Vector2 enemyMediumVel = new Vector2(0f, -0.3f);
    private final Vector2 enemyMediumVelY = new Vector2(0, 0);
    private final Vector2 enemyMediumBulletVelY = new Vector2(0, -0.95f);

    private final Vector2 enemyBigVel = new Vector2(0f, -0.02f);
    private final Vector2 enemyBigVelY = new Vector2(0, 0);
    private final Vector2 enemyBigBulletVelY = new Vector2(0, -1.0f);

    private final TextureRegion bulletRegion;
    private final EnemyPool enemyPool;

    private float generateTimer;

    public EnemyEmitter(Rect worldBounds, Sound shootSound, EnemyPool enemyPool, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.shootSound = shootSound;
        this.enemyPool = enemyPool;
        bulletRegion = atlas.findRegion("bullet_gold");
        enemySmallRegions = Regions.split(atlas.findRegion("enemy_small"), 1, 2, 2);
        enemyMediumRegions = Regions.split(atlas.findRegion("enemy_medium"), 1, 2, 2);
        enemyBigRegions = Regions.split(atlas.findRegion("enemy_big"), 1, 2, 2);

    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0f;
            EnemyShip enemy = enemyPool.obtain();
            float type = (float) Math.random();
            if (type < 0.5f) {
                enemy.set(
                        enemySmallRegions,
                        bulletRegion,
                        enemySmallVel,
                        enemySmallVelY,
                        enemySmallBulletVelY,
                        shootSound,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP,
                        ENEMY_SMALL_BULLET_DAMAGE
                );
            } else if (type < 0.8f) {
                enemy.set(
                        enemyMediumRegions,
                        bulletRegion,
                        enemyMediumVel,
                        enemyMediumVelY,
                        enemyMediumBulletVelY,
                        shootSound,
                        ENEMY_MEDIUM_BULLET_HEIGHT,
                        ENEMY_MEDIUM_RELOAD_INTERVAL,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP,
                        ENEMY_MEDIUM_BULLET_DAMAGE
                );
            } else {
                enemy.set(
                        enemyBigRegions,
                        bulletRegion,
                        enemyBigVel,
                        enemyBigVelY,
                        enemyBigBulletVelY,
                        shootSound,
                        ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP,
                        ENEMY_BIG_BULLET_DAMAGE
                );
            }
            enemy.pos.x = Rnd.nextFloat(
                    worldBounds.getLeft() + enemy.getHalfWidth(),
                    worldBounds.getRight() - enemy.getHalfWidth());
            enemy.setBottom(worldBounds.getTop());
        }
    }
}
