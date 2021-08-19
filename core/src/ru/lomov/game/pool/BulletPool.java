package ru.lomov.game.pool;

import ru.lomov.game.base.SpritesPool;
import ru.lomov.game.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newSprite() {
        return new Bullet();
    }
}
