package ru.lomov.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.lomov.game.base.Sprite;
import ru.lomov.game.math.Rect;

public class Background extends Sprite {
    private final float SIZE= 1.5f;

    public Background(Texture texture) {
        super(new TextureRegion(texture));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(SIZE * worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }
}