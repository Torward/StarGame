package ru.lomov.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.base.BaseScreen;
import ru.lomov.game.math.Rect;
import ru.lomov.game.sprite.Background;
import ru.lomov.game.sprite.Logo;

public class MenuScreen extends BaseScreen {
    private Texture logo;
    private Texture bg;


    private Background background;
    private Logo mainObject;


    @Override
    public void show() {
        super.show();
        logo = new Texture("textures/badlogic.jpg");
        bg = new Texture("textures/cosmos.jpg");
        background = new Background(bg);
        mainObject= new Logo(logo);


    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        mainObject.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();


    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        logo.dispose();

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        mainObject.touchDown(touch, pointer, button);
        return false;
    }
    private void update(float delta){
        mainObject.update(delta);
    }
    private void draw(){
        // batch.enableBlending();
        batch.begin();
        background.draw(batch);
        mainObject.draw(batch);
        batch.end();
    }
}