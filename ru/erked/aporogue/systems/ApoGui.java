package ru.erked.aporogue.ru.erked.aporogue.systems;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.erked.aporogue.StartApoRogue;
import ru.erked.aporogue.ru.erked.aporogue.utilities.Button;

public class ApoGui {

    private Sprite sprite;
    private boolean isDisplayed;
    private Button close;

    public ApoGui(float x, float y, float width, float height, StartApoRogue game){
        sprite = game.textureAtlas.createSprite("gui");
        this.sprite.setBounds(x, y, width, height);
        isDisplayed = false;
        close = new Button(
                x + 0.85f*width,
                y + height - 0.15f*width,
                0.1f*width,
                game.textureAtlas.createSprite("button_close_inactive"),
                game.textureAtlas.createSprite("button_close_active"),
                "",
                1.0f
        );
    }

    public boolean isCloseButtonPressed(ApoIn controller){
        if(controller.isClicked(
                close.getActive().getX(),
                close.getActive().getY(),
                close.getActive().getWidth(),
                close.getActive().getHeight(),
                true,
                false
        )){
            return true;
        } else {
            return false;
        }
    }

    public void draw(SpriteBatch batch, ApoIn controller){
        sprite.draw(batch);
        if(controller.isOn(
                close.getActive().getX(),
                close.getActive().getY(),
                close.getActive().getWidth(),
                close.getActive().getHeight(),
                false
                )){
            close.getActive().draw(batch);
        } else {
            close.getInactive().draw(batch);
        }
    }

    public void setDisplayed(boolean bool){
        isDisplayed = bool;
    }

    public boolean isDisplayed(){
        return isDisplayed;
    }

}
