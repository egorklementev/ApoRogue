package ru.erked.aporogue.ru.erked.aporogue.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.RandomXS128;

import ru.erked.aporogue.StartApoRogue;
import ru.erked.aporogue.ru.erked.aporogue.systems.ApoFont;
import ru.erked.aporogue.ru.erked.aporogue.systems.ApoTextSystem;
import ru.erked.aporogue.ru.erked.aporogue.utilities.Obfuscation;

public class Splash implements Screen{

    StartApoRogue game;
    String logo;
    Obfuscation obf;
    private boolean nextScreen = false;

    public Splash(StartApoRogue game){
        this.game = game;
    }

    @Override
    public void show() {
        game.width = Gdx.graphics.getWidth();
        game.height = Gdx.graphics.getHeight();
        game.batch = new SpriteBatch();
        game.textureAtlas = new TextureAtlas(Gdx.files.internal("texture/texture.atlas"));
        game.textSys = new ApoTextSystem(game.lang);
        game.largeFont = new ApoFont("fonts/normal.ttf", 10, Color.WHITE, 3, 3, Color.BLACK, game.textSys.get("symbols"));
        game.mediumFont = new ApoFont("fonts/normal.ttf", 20, Color.WHITE, 3, 3, Color.BLACK, game.textSys.get("symbols"));
        game.smallFont = new ApoFont("fonts/normal.ttf", 30, Color.WHITE, 3, 3, Color.BLACK, game.textSys.get("symbols"));
        game.rand = new RandomXS128();
        logo = game.textSys.get("game_logo");
        obf = new Obfuscation(game.textureAtlas.createSprite("black"), true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.largeFont.draw(
                game.batch,
                logo,
                0.5F*game.width - 0.5F*(game.largeFont.getWidth(logo)),
                0.5F*game.height + 0.5F*(game.largeFont.getHeight("A"))
        );

        if(obf.isActive() && !nextScreen) {
            obf.deactivate(3.0f, delta);
        }
        if(!obf.isActive()) {
            obf.activate(1.0f, delta);
            nextScreen = true;
        } else if(nextScreen) {
            game.setScreen(new Menu(game));
        }

        obf.draw(game.batch);

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
