package ru.erked.aporogue.ru.erked.aporogue.systems;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import ru.erked.aporogue.StartApoRogue;

public class ApoBody {

    private Sprite sprite;
    private Body body;
    private float meter;
    private int type = -1;

    public ApoBody(
            StartApoRogue game,
            World world,
            BodyDef.BodyType type,
            String texture,
            float meter,
            float x,
            float y,
            float width,
            float height,
            float density,
            float friction,
            float restitution
    ) {
        BodyDef bDef = new BodyDef();
        bDef.type = type;
        bDef.position.set(x + 0.5f, y + 0.5f);
        body = world.createBody(bDef);
        if (type == BodyDef.BodyType.DynamicBody) {
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.5f * width, 0.5f * height);
            FixtureDef fDef = new FixtureDef();
            fDef.shape = shape;
            fDef.friction = friction;
            fDef.density = density;
            fDef.restitution = restitution;
            body.createFixture(fDef);
        }
        if (type == BodyDef.BodyType.StaticBody) {
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.5f * width, 0.5f * height);
            FixtureDef fDef = new FixtureDef();
            fDef.shape = shape;
            fDef.friction = friction;
            body.createFixture(fDef);
        }
        if (type == BodyDef.BodyType.KinematicBody) {
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.5f * width, 0.5f * height);
            FixtureDef fDef = new FixtureDef();
            fDef.shape = shape;
            fDef.friction = friction;
            fDef.restitution = restitution;
            body.createFixture(fDef);
        }
        sprite = game.textureAtlas.createSprite(texture);
        sprite.setSize(meter * width, meter * height);
        sprite.setOriginCenter();

        this.meter = meter;
        this.type = 1;
    }

    public ApoBody(
            StartApoRogue game,
            World world,
            BodyDef.BodyType type,
            String texture,
            float meter,
            float x,
            float y,
            float radius,
            float density,
            float friction,
            float restitution
    ) {
        BodyDef bDef = new BodyDef();
        bDef.type = type;
        bDef.position.set(x + 0.5f, y + 0.5f);
        body = world.createBody(bDef);
        if (type == BodyDef.BodyType.DynamicBody) {
            CircleShape shape = new CircleShape();
            shape.setRadius(radius);
            FixtureDef fDef = new FixtureDef();
            fDef.shape = shape;
            fDef.friction = friction;
            fDef.density = density;
            fDef.restitution = restitution;
            body.createFixture(fDef);
        }
        if (type == BodyDef.BodyType.StaticBody) {
            CircleShape shape = new CircleShape();
            shape.setRadius(radius);
            FixtureDef fDef = new FixtureDef();
            fDef.shape = shape;
            fDef.friction = friction;
            body.createFixture(fDef);
        }
        if (type == BodyDef.BodyType.KinematicBody) {
            CircleShape shape = new CircleShape();
            shape.setRadius(radius);
            FixtureDef fDef = new FixtureDef();
            fDef.shape = shape;
            fDef.friction = friction;
            fDef.restitution = restitution;
            body.createFixture(fDef);
        }
        sprite = game.textureAtlas.createSprite(texture);
        sprite.setSize(meter * 2.0f * radius, meter * 2.0f * radius);
        sprite.setOriginCenter();

        this.meter = meter;
        this.type = 2;
    }

    public ApoBody(
            StartApoRogue game,
            World world,
            BodyDef.BodyType type,
            String texture,
            float meter,
            float x,
            float y,
            float width,
            float height,
            Vector2[] vec,
            float density,
            float friction,
            float restitution
    ) {
        BodyDef bDef = new BodyDef();
        bDef.type = type;
        bDef.position.set(x + 0.5f, y + 0.5f);
        body = world.createBody(bDef);
        if (type == BodyDef.BodyType.DynamicBody) {
            PolygonShape shape = new PolygonShape();
            shape.set(vec);
            FixtureDef fDef = new FixtureDef();
            fDef.shape = shape;
            fDef.friction = friction;
            fDef.density = density;
            fDef.restitution = restitution;
            body.createFixture(fDef);
        }
        if (type == BodyDef.BodyType.StaticBody) {
            PolygonShape shape = new PolygonShape();
            shape.set(vec);
            FixtureDef fDef = new FixtureDef();
            fDef.shape = shape;
            fDef.friction = friction;
            body.createFixture(fDef);
        }
        if (type == BodyDef.BodyType.KinematicBody) {
            PolygonShape shape = new PolygonShape();
            shape.set(vec);
            FixtureDef fDef = new FixtureDef();
            fDef.shape = shape;
            fDef.friction = friction;
            fDef.restitution = restitution;
            body.createFixture(fDef);
        }
        sprite = game.textureAtlas.createSprite(texture);
        sprite.setSize(meter * width, meter * height);
        sprite.setOrigin(0f, 0f);

        this.meter = meter;
        this.type = 3;
    }

    public void update() {
        switch (type){
            case 1: case 2:{
                sprite.setPosition(
                        meter * body.getPosition().x - 0.5f * sprite.getWidth(),
                        meter * body.getPosition().y - 0.5f * sprite.getHeight()
                );
                sprite.setRotation((float)Math.toDegrees(body.getAngle()));
                break;
            }
            case 3:{
                sprite.setPosition(
                        meter * body.getPosition().x,
                        meter * body.getPosition().y
                );
                sprite.setRotation((float)Math.toDegrees(body.getAngle()));
                break;
            }
            default:{
                break;
            }
        }
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void applyLinearImpulse(float xImp, float yImp, float xPoint, float yPoint, boolean wake){
        body.applyLinearImpulse(new Vector2(xImp, yImp), new Vector2(xPoint, yPoint), wake);
    }

    public void applyForceToTheCentre(float x, float y, boolean wake){
        body.applyForceToCenter(new Vector2(x, y), wake);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Sprite getSprite(){
        return sprite;
    }

    public Body getBody(){
        return body;
    }

}
