package ru.erked.aporogue;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.RandomXS128;

import ru.erked.aporogue.ru.erked.aporogue.screens.Splash;
import ru.erked.aporogue.ru.erked.aporogue.systems.ApoFont;
import ru.erked.aporogue.ru.erked.aporogue.systems.ApoIn;
import ru.erked.aporogue.ru.erked.aporogue.systems.ApoTextSystem;

public class StartApoRogue extends Game {

    public float width;
    public float height;
	public SpriteBatch batch;
    public TextureAtlas textureAtlas;
    public ApoTextSystem textSys;
    public ApoFont smallFont;
    public ApoFont mediumFont;
    public ApoFont largeFont;
    public int lang;
    public static ApoIn controller;
    public RandomXS128 rand;

    public StartApoRogue(int lang){
        this.lang = lang;
    }

	@Override
	public void create () {
        setScreen(new Splash(this));
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {
        batch = new SpriteBatch();
        textureAtlas = new TextureAtlas(Gdx.files.internal("texture/texture.atlas"));
	}

	@Override
	public void dispose () {
        textureAtlas.dispose();
	}
}
