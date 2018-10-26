package ru.erked.aporogue.ru.erked.aporogue.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import ru.erked.aporogue.StartApoRogue;
import ru.erked.aporogue.ru.erked.aporogue.systems.ApoBody;
import ru.erked.aporogue.ru.erked.aporogue.systems.ApoContactListener;

public class Game implements Screen {

    private StartApoRogue game;

    private static final float STEP_TIME = 1f / 60f;
    private static final int VELOCITY_ITERATIONS = 5;
    private static final int POSITION_ITERATIONS = 3;
    private float accumulator = 0f;

    private float worldWidth;
    private float worldHeight;
    private float meter;
    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private short[][] map;
    private ArrayList<ApoBody> bMap;

    private ApoBody player;
    private ApoBody barrel;

    public Game(StartApoRogue game){
        this.game = game;
        worldWidth = 15f;
        worldHeight = worldWidth*(game.height/game.width);
        meter = game.width / 15f;
        map = new short[(int)worldHeight][(int)worldWidth];
        bMap = new ArrayList<ApoBody>();
        camera = new OrthographicCamera(worldWidth, worldHeight);
        camera.position.x = 0.5f*worldWidth;
        camera.position.y = 0.5f*worldHeight;
        renderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -9.8f), true);
        world.setContactListener(new ApoContactListener());

        player = new ApoBody(
                game,
                world,
                BodyDef.BodyType.DynamicBody,
                "knight",
                meter,
                1.9f,
                3f,
                1f,
                2f,
                1.0f,
                0.0f,
                0.0f
        );
        player.getBody().getFixtureList().get(0).setUserData("player");

        barrel = new ApoBody(
                game,
                world,
                BodyDef.BodyType.DynamicBody,
                "barrel",
                meter,
                3.9f,
                3f,
                0.5f,
                1.0f,
                0.0f,
                0.0f
        );

    }

    private void generateWorld() {
        for(int i = 0; i < (int)worldHeight; ++i){
            for(int j = 0; j < (int)worldWidth; ++j){
                map[i][j] = 0;
            }
        }
        for(int j = 1; j < (int)worldWidth - 1; ++j){
            map[0][j] = 2;
        }
        for(int j = 0; j < (int)worldWidth; ++j){
            map[(int)worldHeight - 1][j] = 2;
        }
        for(int i = 0; i < (int)worldHeight - 1; ++i){
            map[i][0] = 1;
            map[i][(int)worldWidth - 1] = 1;
        }
        for(int i = 0; i < (int)worldHeight; ++i){
            for(int j = 0; j < (int)worldWidth; ++j){
                switch (map[i][j]){
                    case 1: {
                        ApoBody ground = new ApoBody(
                                game,
                                world,
                                BodyDef.BodyType.StaticBody,
                                "dirt",
                                meter,
                                j,
                                i,
                                1f,
                                1f,
                                0f,
                                0.2f,
                                0f

                        );
                        ground.update();
                        bMap.add(ground);
                        break;
                    }
                    case 2:{
                        ApoBody ground = new ApoBody(
                                game,
                                world,
                                BodyDef.BodyType.StaticBody,
                                "grass",
                                meter,
                                j,
                                i,
                                1f,
                                1f,
                                0f,
                                0.2f,
                                0f

                        );
                        ground.update();
                        bMap.add(ground);
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        }
    }
    private void drawWorld() {
        for(int i = 0; i < bMap.size(); ++i) {
            bMap.get(i).draw(game.batch);
        }
    }

    @Override
    public void show() {
        Box2D.init();
        generateWorld();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.698f, 0.698f, 0.698f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        player.getBody().setTransform(player.getPosition(), 0f);
        player.update();
        player.draw(game.batch);

        barrel.update();
        barrel.draw(game.batch);

        drawWorld();

        game.batch.end();

        camera.update();
        renderer.render(world, camera.combined);
        accumulator += Math.min(delta, 0.25f);
        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }

        boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        if(left){
            player.getBody().setLinearVelocity(-2f, player.getBody().getLinearVelocity().y);
        }
        if(right){
            player.getBody().setLinearVelocity(2f, player.getBody().getLinearVelocity().y);
        }
        if(!left && !right){
            player.getBody().setLinearVelocity(0f, player.getBody().getLinearVelocity().y);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            player.applyLinearImpulse(
                    0f,
                    10f,
                    player.getPosition().x,
                    player.getPosition().y,
                    true
            );
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.applyForceToTheCentre(
                    0f,
                    -5f,
                    true
            );
        }
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
