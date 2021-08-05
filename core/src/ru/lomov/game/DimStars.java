package ru.lomov.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.lomov.game.screen.MenuScreen;

public class DimStars extends Game {


	@Override
	public void create() {
		setScreen(new MenuScreen());
	}
}