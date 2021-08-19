package ru.lomov.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.base.Sprite;
import ru.lomov.game.math.Rect;

public class Bullet extends Sprite {
    private Rect worldBounds;
    private Vector2 vel;
    private int damage;
    private Sprite owner;
    private long shootSound;

    public Bullet() {
        regions = new TextureRegion[1];
        vel = new Vector2();
    }

    public void set(
            Sprite owner,
            TextureRegion region,
            Vector2 pos0,
            Vector2 vel0,
            float height,
            Rect worldBounds,
            int damage,
            long shootSound
    ) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.vel.set(vel0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
        this.shootSound = shootSound;
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(vel, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public Sprite getOwner() {
        return owner;
    }
}
