package ru.erked.aporogue.ru.erked.aporogue.utilities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Button {

    Sprite spriteInactive, spriteActive;
    String text;

    public Button(float x, float y, float width, Sprite inactive, Sprite active, String text){
        this.text = text;
        spriteInactive = inactive;
        spriteActive = active;
        spriteInactive.setBounds(x, y, width, 0.25f*width);
        spriteActive.setBounds(x, y, width, 0.25f*width);
    }

    public Button(float x, float y, float width, Sprite inactive, Sprite active, String text, float aspectRatio){
        this.text = text;
        spriteInactive = inactive;
        spriteActive = active;
        spriteInactive.setBounds(x, y, width, aspectRatio*width);
        spriteActive.setBounds(x, y, width, aspectRatio*width);
    }

    public String getText(){
        return text;
    }

    public Sprite getInactive(){
        return spriteInactive;
    }

    public Sprite getActive(){
        return spriteActive;
    }
}
