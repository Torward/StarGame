package ru.lomov.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.base.Sprite;
import ru.lomov.game.math.Rect;
import ru.lomov.game.math.Rnd;

public class Star extends Sprite {

    private final Vector2 VEL;
    private Rect worldBounds;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        VEL = new Vector2(/*Rnd.nextFloat(-0.001f, -0.0015f), getHeight()*-10 Rnd.nextFloat(-0.0001f, -0.005f)*/);
    }

    @Override
    public void update(float delta) {
        checkAndHandleBounds();
        pos.mulAdd(VEL, delta);
    }

    @Override
    public void resize(Rect worldBounds) {
        this. worldBounds = worldBounds;
        float height = Rnd.nextFloat(0.0005f,0.002f);
        setHeightProportion(height);
        float x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float y = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(x,y);
        VEL.set(Rnd.nextFloat(-0.001f, 0.0015f), getHeight()*-1);
    }
    private void checkAndHandleBounds(){
        if (getRight()< worldBounds.getLeft()){
            setLeft(worldBounds.getRight());
        }
        if (getLeft()> worldBounds.getRight()){
            setRight(worldBounds.getLeft());
        }

        if (getTop()< worldBounds.getBottom()){
            setBottom(worldBounds.getTop());
        }
        if (getBottom()> worldBounds.getTop()){
            setTop(worldBounds.getBottom());
        }
    }
}
