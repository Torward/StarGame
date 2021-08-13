package ru.lomov.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.base.Sprite;
import ru.lomov.game.math.Rect;

public class Logo extends Sprite {

    private final float V_LEN = 0.001f;
    private final Vector2 vel;
    private final float SIZE=0.1f;

    private Vector2 touch;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        this.vel = new Vector2();
        this.touch = new Vector2();
    }
    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(SIZE *worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }

    @Override
    public void update(float delta) {
        if(touch.dst(pos)> V_LEN){
            pos.add(vel);
        } else {
            pos.set(touch);

        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
       this.touch.set(touch);
        vel.set(touch.cpy().sub(pos).setLength(V_LEN));
        return false;

    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }
}
