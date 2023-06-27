package org.eu.hanana.cirno.csgo.api;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class GuiBase {
    public int x,y,dx,dy;
    public List<ButtonBase> buttonList = new ArrayList<>();
    public void drawAll(SpriteBatch batch){
        drawBackground(batch);
        drawForeground(batch);
        drawTooltip(batch);
    }
    public void init(){
        buttonList.clear();
        size();
    }
    public abstract void size();
    public abstract void drawBackground(SpriteBatch batch);
    public abstract void drawForeground(SpriteBatch batch);
    public void drawTooltip(SpriteBatch batch){

    }
    public abstract void dispose();
}
