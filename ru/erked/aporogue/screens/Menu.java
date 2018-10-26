package ru.erked.aporogue.ru.erked.aporogue.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

import ru.erked.aporogue.StartApoRogue;
import ru.erked.aporogue.ru.erked.aporogue.systems.ApoGui;
import ru.erked.aporogue.ru.erked.aporogue.systems.ApoIn;
import ru.erked.aporogue.ru.erked.aporogue.utilities.Button;
import ru.erked.aporogue.ru.erked.aporogue.utilities.Obfuscation;

public class Menu implements Screen {

    private StartApoRogue game;
    private Button[] buttons;
    private Obfuscation obf;
    private short[][] map;
    private short[][] mapDecor;
    private ArrayList<Sprite> sMap, sMapDecor, clouds;
    private Sprite sun;
    private Music menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/music/menu_music.mp3"));
    private ApoGui aboutGui;

    public Menu(StartApoRogue game){
        this.game = game;
        game.controller = new ApoIn();
        Gdx.input.setInputProcessor(game.controller);
        buttons = new Button[3];
        obf = new Obfuscation(game.textureAtlas.createSprite("black"), true);
        map = new short[10][15];
        mapDecor = new short[10][15];
        aboutGui = new ApoGui(
                0.25f*game.width,
                0.5f*(game.height - 0.5f*game.width),
                0.5f*game.width,
                0.5f*game.width,
                game
        );
        mapFilling();
    }

    private void mapFilling(){
        for(int i = 0; i < 10; ++i){
            for(int j = 0; j < 15; ++j){
                map[i][j] = 0;
                mapDecor[i][j] = 0;
            }
        }
        for(int j = 0; j < 15; ++j){
            map[9][j] = 2;
        }
        for(int j = 0; j < 6; ++j){
            map[8][j] = 2;
            map[9][j] = 1;
        }
        for(int j = 0; j < 2; ++j){
            map[7][j] = 2;
            map[8][j] = 1;
        }
        for(int j = 6; j < 15; ++j){
            if(game.rand.nextInt(2) > 0) mapDecor[8][j] = (short)(game.rand.nextInt(6) + 1);
        }
        for(int j = 2; j < 5; ++j){
            if(game.rand.nextInt(2) > 0) mapDecor[7][j] = (short)(game.rand.nextInt(6) + 1);
        }
        for(int j = 0; j < 1; ++j){
            if(game.rand.nextInt(2) > 0) mapDecor[6][j] = (short)(game.rand.nextInt(6) + 1);
        }
        sun = game.textureAtlas.createSprite("sun");
        sun.setBounds(
                game.width - 2.0f*game.width/15.0f,
                game.height - 2.0f*game.width/15.0f,
                game.width/15.0f, game.width/15.0f
        );
        sMap = new ArrayList<Sprite>();
        sMapDecor = new ArrayList<Sprite>();
        for(int i = 0; i < 150; ++i){
            switch (map[i / 15][i % 15]){
                case 1:{
                    Sprite dirt;
                    dirt = game.textureAtlas.createSprite("dirt");
                    dirt.setBounds(0, 0, game.width/15.0f, game.width/15.0f);
                    dirt.setX((i % 15)*(game.width/15.0f));
                    dirt.setY((9 - i / 15)*(game.width/15.0f));
                    sMap.add(dirt);
                    break;
                }
                case 2:{
                    Sprite grass;
                    grass = game.textureAtlas.createSprite("grass");
                    grass.setBounds(0, 0, game.width/15.0f, game.width/15.0f);
                    grass.setX((i % 15)*(game.width/15.0f));
                    grass.setY((9 - i / 15)*(game.width/15.0f));
                    sMap.add(grass);
                    break;
                }
                default:{
                    break;
                }
            }
            switch (mapDecor[i / 15][i % 15]){
                case 1:{
                    Sprite bush;
                    bush = game.textureAtlas.createSprite("bush", 1);
                    bush.setBounds(0, 0, game.width/15.0f, game.width/15.0f);
                    bush.setX((i % 15)*(game.width/15.0f));
                    bush.setY((9 - i / 15)*(game.width/15.0f));
                    sMapDecor.add(bush);
                    break;
                }
                case 2:{
                    Sprite bush;
                    bush = game.textureAtlas.createSprite("bush", 2);
                    bush.setBounds(0, 0, game.width/15.0f, game.width/15.0f);
                    bush.setX((i % 15)*(game.width/15.0f));
                    bush.setY((9 - i / 15)*(game.width/15.0f));
                    sMapDecor.add(bush);
                    break;
                }
                case 3:{
                    Sprite bush;
                    bush = game.textureAtlas.createSprite("bush", 3);
                    bush.setBounds(0, 0, 2.0f*game.width/15.0f, game.width/15.0f);
                    bush.setX((i % 15)*(game.width/15.0f));
                    bush.setY((9 - i / 15)*(game.width/15.0f));
                    sMapDecor.add(bush);
                    break;
                }
                case 4:{
                    Sprite bush;
                    bush = game.textureAtlas.createSprite("bush", 4);
                    bush.setBounds(0, 0, 2.0f*game.width/15.0f, game.width/15.0f);
                    bush.setX((i % 15)*(game.width/15.0f));
                    bush.setY((9 - i / 15)*(game.width/15.0f));
                    sMapDecor.add(bush);
                    break;
                }
                case 5:{
                    Sprite tallGrass;
                    tallGrass = game.textureAtlas.createSprite("tall_grass", 1);
                    tallGrass.setBounds(0, 0, game.width/15.0f, game.width/15.0f);
                    tallGrass.setX((i % 15)*(game.width/15.0f));
                    tallGrass.setY((9 - i / 15)*(game.width/15.0f));
                    sMapDecor.add(tallGrass);
                    break;
                }
                case 6:{
                    Sprite tallGrass;
                    tallGrass = game.textureAtlas.createSprite("tall_grass", 2);
                    tallGrass.setBounds(0, 0, game.width/15.0f, game.width/15.0f);
                    tallGrass.setX((i % 15)*(game.width/15.0f));
                    tallGrass.setY((9 - i / 15)*(game.width/15.0f));
                    sMapDecor.add(tallGrass);
                    break;
                }
                default:{
                    break;
                }
            }
        }
        clouds = new ArrayList<Sprite>();
        for(int i = 0; i < game.rand.nextInt(10) + 15; ++i){
            Sprite cloud = game.textureAtlas.createSprite("cloud", game.rand.nextInt(3) + 1);
            float width = (game.rand.nextFloat() + 0.5f)*0.25f*game.width;
            float x = 0.0f - 0.5f*game.width + (game.rand.nextInt(10) + 1)*width;
            float y = game.height - (game.rand.nextInt(2) + 2)*0.25f*width;
            cloud.setBounds(x, y, width, 0.5f*width);
            clouds.add(cloud);
        }
    }
    private void mapDraw(float delta){
        for(int i = 0; i < sMap.size(); ++i) {
            sMap.get(i).draw(game.batch);
        }
        for(int i = 0; i < sMapDecor.size(); ++i) {
            sMapDecor.get(i).draw(game.batch);
        }
        sun.draw(game.batch);
        for(int i = 0; i < clouds.size(); ++i){
            clouds.get(i).draw(game.batch);
            clouds.get(i).setX(clouds.get(i).getX() + 20.0f*delta);
            if(clouds.get(i).getX() > game.width){
                float width = (game.rand.nextFloat() + 0.5f)*0.25f*game.width;
                float x = 0.0f - (game.rand.nextInt(5) + 1)*width;
                float y = game.height - (game.rand.nextInt(2) + 2)*0.25f*width;
                clouds.get(i).setBounds(x, y, width, 0.5f*width);
            }
        }
    }
    private void buttonsInit(){
        Button startGame = new Button(
                0.65f*game.width,
                0.5f*game.height - 0.275f*0.3f*game.width,
                0.3f*game.width,
                game.textureAtlas.createSprite("button_inactive"),
                game.textureAtlas.createSprite("button_active"),
                game.textSys.get("start_game")
        );
        Button aboutGame = new Button(
                0.65f*game.width,
                0.5f*game.height - 0.55f*0.3f*game.width,
                0.3f*game.width,
                game.textureAtlas.createSprite("button_inactive"),
                game.textureAtlas.createSprite("button_active"),
                game.textSys.get("about_game")
        );
        Button exitGame = new Button(
                0.65f*game.width,
                0.5f*game.height - 0.825f*0.3f*game.width,
                0.3f*game.width,
                game.textureAtlas.createSprite("button_inactive"),
                game.textureAtlas.createSprite("button_active"),
                game.textSys.get("exit_game")
        );
        buttons[0] = startGame;
        buttons[1] = aboutGame;
        buttons[2] = exitGame;
    }
    private void buttonsListener(){
        if(!aboutGui.isDisplayed()) {
            if (game.controller.isClicked(
                    buttons[0].getInactive().getX(),
                    buttons[0].getInactive().getY(),
                    buttons[0].getInactive().getWidth(),
                    buttons[0].getInactive().getHeight(),
                    true,
                    false
            )) {
                game.setScreen(new Game(game));
                this.dispose();
            }

            if (game.controller.isClicked(
                    buttons[1].getInactive().getX(),
                    buttons[1].getInactive().getY(),
                    buttons[1].getInactive().getWidth(),
                    buttons[1].getInactive().getHeight(),
                    true,
                    false
            )) {
                aboutGui.setDisplayed(true);
            }

            if (game.controller.isClicked(
                    buttons[2].getInactive().getX(),
                    buttons[2].getInactive().getY(),
                    buttons[2].getInactive().getWidth(),
                    buttons[2].getInactive().getHeight(),
                    true,
                    false
            )) {
                game.dispose();
                this.dispose();
                Gdx.app.exit();
            }
        } else if(aboutGui.isCloseButtonPressed(game.controller) && aboutGui.isDisplayed()){
            aboutGui.setDisplayed(false);
        }
    }
    private void buttonsDraw(){
        for(int i = 0; i < 3; ++i) {
            if(game.controller.isOn(
                    buttons[i].getInactive().getX(),
                    buttons[i].getInactive().getY(),
                    buttons[i].getInactive().getWidth(),
                    buttons[i].getInactive().getHeight(),
                    false
            ) && !aboutGui.isDisplayed()) {
                buttons[i].getActive().draw(game.batch);
            }else{
                buttons[i].getInactive().draw(game.batch);
            }
            game.smallFont.draw(
                    game.batch,
                    buttons[i].getText(),
                    buttons[i].getActive().getX() + 0.5f*(buttons[i].getActive().getWidth() -
                            game.smallFont.getWidth(buttons[i].getText())),
                    buttons[i].getActive().getY() + 0.5f*(buttons[i].getActive().getHeight() +
                            game.smallFont.getHeight("A"))
            );
        }
    }
    private void aboutGuiDraw(){
        game.smallFont.draw(
                game.batch,
                game.textSys.get("author"),
                0.5f*(game.width - game.smallFont.getWidth(game.textSys.get("author"))),
                0.5f*(game.height - 0.5f*game.width) + 0.4f*game.width
        );
        game.smallFont.draw(
                game.batch,
                game.textSys.get("amazing_name"),
                0.5f*(game.width - game.smallFont.getWidth(game.textSys.get("amazing_name"))),
                0.5f*(game.height - 0.5f*game.width) + 0.4f*game.width - 1.5f*game.smallFont.getHeight("A")
        );
        game.smallFont.draw(
                game.batch,
                game.textSys.get("contacts"),
                0.5f*(game.width - game.smallFont.getWidth(game.textSys.get("contacts"))),
                0.5f*(game.height - 0.5f*game.width) + 0.4f*game.width - 4.5f*game.smallFont.getHeight("A")
        );
        game.smallFont.draw(
                game.batch,
                game.textSys.get("email"),
                0.5f*(game.width - game.smallFont.getWidth(game.textSys.get("email"))),
                0.5f*(game.height - 0.5f*game.width) + 0.4f*game.width - 6.0f*game.smallFont.getHeight("A")
        );
    }

    @Override
    public void show() {
        menuMusic.setVolume(0.5f);
        menuMusic.setLooping(true);
        menuMusic.play();

        buttonsInit();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.698f, 0.698f, 0.698f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        mapDraw(delta);
        game.mediumFont.draw(game.batch, "ApoRogue", 0.01f*game.width, 0.975f*game.height);
        buttonsDraw();
        if(aboutGui.isDisplayed()) {
            aboutGui.draw(game.batch, game.controller);
            aboutGuiDraw();
        }
        if(obf.isActive()) obf.deactivate(1.0f, delta);
        obf.draw(game.batch);

        game.batch.end();

        buttonsListener();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        menuMusic.pause();
    }

    @Override
    public void resume() {
        menuMusic.play();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        menuMusic.stop();
    }
}
