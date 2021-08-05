package ru.lomov.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.base.BaseScreen;
import ru.lomov.game.math.Rect;
import ru.lomov.game.sprite.Background;

public class MenuScreen extends BaseScreen {
    private Texture img;
    private Texture bg;

    private Vector2 pos;
    private Background background;
//    private Vector2 vel;
//    private Vector2 posA;
//    private Vector2 dir;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/cosmos.jpg");
        background = new Background(bg);
        pos = new Vector2();

//        posA = new Vector2();
//        vel = new Vector2();
//        dir = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.enableBlending();
        batch.begin();
        background.draw(batch);
        batch.end();
//        if(pos.dst2(posA)> vel.dst2(dir)){
//            pos.add(vel);
//        } else {
//            pos.set(posA);
//
//        }

    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);
    }
}