package ru.lomov.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.base.Sprite;
import ru.lomov.game.math.Rect;

public class MainShip extends Sprite {

//    private final float V_LEN = 0.001f;
//    private  Vector2 vel;
//    private final float SIZE=0.1f;

    private Vector2 touch;

    public MainShip(TextureAtlas atlas) {
        super(atlas.findRegion("hero_ship"), 1, 2, 2);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.15f);
        //setBottom(worldBounds.getBottom() + 0.08f);
        //setLeft(worldBounds.getLeft());
    }

    @Override
    public void update(float delta) {
        super.update(delta);

    }

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public boolean mouseMoved(Vector2 touch) {
        return super.mouseMoved(touch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
       this.touch.set(touch);
//        vel.set(touch.cpy().sub(pos).setLength(V_LEN));
        return false;

    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }
}
