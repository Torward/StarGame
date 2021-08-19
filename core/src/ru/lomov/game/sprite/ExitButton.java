package ru.lomov.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.lomov.game.base.BaseButton;
import ru.lomov.game.math.Rect;

public class ExitButton extends BaseButton {

    private static final float PADDING = 0.001f;

    public ExitButton(TextureAtlas atlas) {
        super(atlas.findRegion("btn_exit_pushed"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.1f);
        setTop(worldBounds.getTop() - 0.02f);
        setLeft(worldBounds.getLeft() + PADDING);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
