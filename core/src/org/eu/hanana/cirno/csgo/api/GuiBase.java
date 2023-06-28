package org.eu.hanana.cirno.csgo.api;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public abstract class GuiBase {
    public int x,y,dx,dy;
    public List<ButtonBase> buttonList = new ArrayList<>();
    public void drawAll(SpriteBatch batch, ShapeRenderer shapeRenderer){
        drawBackground(batch,shapeRenderer);
        drawForeground(batch,shapeRenderer);
        drawTooltip(batch,shapeRenderer);
    }
    public void init(){
        buttonList.clear();
        size();
    }
    public void action(int id){}
    public abstract void size();
    public abstract void drawBackground(SpriteBatch batch, ShapeRenderer shapeRenderer);
    public void drawForeground(SpriteBatch batch, ShapeRenderer shapeRenderer){
        for (ButtonBase buttonBase : buttonList) {
            buttonBase.draw(batch,shapeRenderer);
        }
    }
    public void drawTooltip(SpriteBatch batch, ShapeRenderer shapeRenderer){

    }
    public abstract void dispose();
}
