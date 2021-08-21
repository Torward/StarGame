package ru.lomov.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.base.Ship;
import ru.lomov.game.math.Rect;
import ru.lomov.game.pool.BulletPool;

public class EnemyShip extends Ship {


   public EnemyShip(Rect worldBounds, BulletPool bulletPool) {
      super();
      this.worldBounds = worldBounds;
      this.bulletPool = bulletPool;
      shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/blast.wav"));

   }

   public void set(
           TextureRegion[] regions,
           TextureRegion bulletRegion,
           Vector2 vel0,
           Vector2 velY,
           Vector2 bulletVel,
           Sound shootSound,
           float bulletHeight,
           float reloadInterval,
           float height,
           int hp,
           int bulletDamage
   ){
      this.regions = regions;
      this.bulletRegion = bulletRegion;
      this.vel0.set(vel0);
      this.velY.set(velY);
      this.bulletVel.set(bulletVel);
      this.shootSound = shootSound;
      this.bulletHeight = bulletHeight;
      this.reloadInterval = reloadInterval;
      setHeightProportion(height);
      this.hp = hp;
      this.bulletDamage = bulletDamage;
      vel.set(0, -0.4f);
      bulletPos1.set(pos.x + getHalfWidth() - 0.006f, pos.y + 0.007f);
      bulletPos2.set(pos.x - getHalfWidth() + 0.028f, pos.y + 0.007f);

   }

   @Override
   public void update(float delta) {
      super.update(delta);
      if (getTop()<worldBounds.getTop()){
         vel.set(vel0);
      } else {
         reloadTimer =  reloadInterval*0.8f;
      }
      if (getBottom()<worldBounds.getBottom()){
         destroy();
      }
      bulletPos1.set(pos.x + getHalfWidth() - 0.006f, pos.y + 0.007f);
      bulletPos2.set(pos.x - getHalfWidth() + 0.028f, pos.y + 0.007f);
   }
}
