package ru.erked.aporogue.ru.erked.aporogue.systems;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class ApoContactListener implements ContactListener{

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        WorldManifold manifold = contact.getWorldManifold();

        for(int j = 0; j < manifold.getNumberOfContactPoints(); j++){
            if(contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("player")) {

            }
            if(contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("player")) {

            }
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
