package ru.erked.aporogue.ru.erked.aporogue.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Obfuscation {

    private Sprite sprite;
    private float alpha;
    private boolean isActive;

    public Obfuscation(Sprite sprite, boolean isActive){
        this.sprite = sprite;
        sprite.setBounds(0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.isActive = isActive;
        if(isActive) alpha = 1.0f;
        else alpha = 0.0f;
    }

    public void activate(float time, float delta){
        if(alpha + delta/time < 1.0f) {
            alpha += delta/time;
            sprite.setAlpha(alpha);
        } else {
            alpha = 1.0f;
            sprite.setAlpha(alpha);
            isActive = true;
        }
    }

    public void deactivate(float time, float delta){
        if(alpha - delta/time > 0.0f) {
            alpha -= delta/time;
            sprite.setAlpha(alpha);
        } else {
            alpha = 0.0f;
            sprite.setAlpha(alpha);
            isActive = false;
        }
    }

    public boolean isActive(){
        return isActive;
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

}
