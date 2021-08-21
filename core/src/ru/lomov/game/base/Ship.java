package ru.lomov.game.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.math.Rect;
import ru.lomov.game.pool.BulletPool;
import ru.lomov.game.sprite.Bullet;

public class Ship extends Sprite{
//    protected SpriteBatch batch;
//    protected TextureRegion region;
//    protected float scaleX;
//    protected float scaleY;


    protected Vector2 vel0;
    protected Vector2 velY;
    protected Vector2 vel;

    protected Rect worldBounds;

    protected Vector2 touch;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletPos1;
    protected Vector2 bulletPos2;
    protected Vector2 bulletPos3;
    protected Vector2 bulletPos4;

    protected Vector2 bulletVel;
    protected float bulletHeight;
    protected int bulletDamage;
    protected int hp;

    protected Sound shootSound;

    protected float reloadInterval;
    protected float reloadTimer;
    protected Sound bulletSound;

    public Ship() {
        vel0 = new Vector2();
        velY = new Vector2();
        vel = new Vector2();
        bulletPos1 = new Vector2();
        bulletPos2 = new Vector2();
        bulletVel = new Vector2();
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        vel0 = new Vector2();
        velY = new Vector2();
        vel = new Vector2();
        bulletPos1 = new Vector2();
        bulletPos2 = new Vector2();
        bulletVel = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(vel, delta);
        reloadTimer += delta;
        if (reloadTimer>= reloadInterval){
            reloadTimer = 0f;
            shoot();
        }
    }
    private void shoot() {
        Bullet bullet1 = bulletPool.obtain();
        Bullet bullet2 = bulletPool.obtain();
//        Bullet bullet3 = bulletPool.obtain();
        //Bullet bullet4 = bulletPool.obtain();
        //bulletPos1.set(pos.x + getHalfWidth() - 0.006f, pos.y + 0.007f);
//        bulletPos2.set(pos.x + getHalfWidth() - 0.016f, pos.y + 0.007f);
//        bulletPos3.set(pos.x - getHalfWidth() + 0.046f, pos.y + 0.007f);
//        bulletPos4.set(pos.x - getHalfWidth() + 0.036f, pos.y + 0.007f);
        bullet1.set(this, bulletRegion, bulletPos1, bulletVel, bulletHeight, worldBounds, bulletDamage, shootSound.play());
        bullet2.set(this, bulletRegion, bulletPos2, bulletVel, bulletHeight, worldBounds, bulletDamage, shootSound.play());
//        bullet3.set(this, bulletRegion, bulletPos3, bulletVel, bulletHeight, worldBounds, bulletDamage, shootSound.play());
        //bullet4.set(this, bulletRegion, bulletPos4, bulletVel, bulletHeight, worldBounds, bulletDamage, shootSound.play());

    }
}
