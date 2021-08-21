package ru.lomov.game.pool;

import ru.lomov.game.base.SpritesPool;
import ru.lomov.game.math.Rect;
import ru.lomov.game.sprite.Bullet;
import ru.lomov.game.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {
    private final Rect worldBounds;
    private final BulletPool bulletPool;

    public EnemyPool(Rect worldBounds, BulletPool bulletPool) {
        this.worldBounds = worldBounds;
        this.bulletPool = bulletPool;
    }

    @Override
    protected EnemyShip newSprite() {
        return new EnemyShip(worldBounds, bulletPool);
    }
}
