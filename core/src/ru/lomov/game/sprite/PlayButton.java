package ru.lomov.game.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.lomov.game.base.BaseButton;
import ru.lomov.game.math.Rect;
import ru.lomov.game.screen.GameScreen;

public class PlayButton extends BaseButton {

    private final Game game;
    private Sound startSound;

    public PlayButton(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btn_play_pushed"));
        this.game = game;
    }


    @Override
    public void action() {
       startSound = Gdx.audio.newSound(Gdx.files.internal("sounds/start_btn.wav"));
        startSound.play();
        game.setScreen(new GameScreen());
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.1f);
        setTop(worldBounds.getTop() - 0.025f);
        setLeft(worldBounds.getLeft()+0.3f);

    }
}
