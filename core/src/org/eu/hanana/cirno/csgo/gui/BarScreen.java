package org.eu.hanana.cirno.csgo.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import org.eu.hanana.cirno.csgo.Props;
import org.eu.hanana.cirno.csgo.api.BtnCallback;
import org.eu.hanana.cirno.csgo.api.ButtonBase;
import org.eu.hanana.cirno.csgo.api.GuiBase;

import javax.security.auth.callback.Callback;
import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Gdx.gl;

public class BarScreen extends GuiBase {
    private final Map<Integer, BtnCallback> callbacks = new HashMap<>();
    private static BarScreen Instance;
    public BarScreen(){
        Instance=this;

    }

    public static BarScreen getInstance() {
        return Instance;
    }

    @Override
    public void init() {
        super.init();
        //buttonList.add(new ButtonBase(0,x+Props.getXSize(0.03f),y+Props.getYSize(0.03f),Props.getXSize(0.1f),Props.getYSize(0.08f),"X"));
    }
    public void addbtn(String text, BtnCallback callback,float width){
        buttonList.add(new ButtonBase(buttonList.size(),x+Props.getXSize(0.03f)+Props.getXSize(0.11f)*buttonList.size(),y+Props.getYSize(0.01f),Props.getXSize(width),Props.getYSize(0.08f),text,this));
        callbacks.put(buttonList.size()-1,callback);
    }

    @Override
    public void action(int id) {
        super.action(id);
        if (callbacks.containsKey(id))
            callbacks.get(id).onClick();
    }

    @Override
    public void size() {
        x=0;
        dy=Props.getYSize(0.1f);
        y=Props.getYSize(1)-dy;
        dx=Props.getXSize(1);
    }
    @Override
    public void drawBackground(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        ScreenUtils.clear(Color.GREEN);
    }

    @Override
    public void drawForeground(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super.drawForeground(batch,shapeRenderer);

    }

    @Override
    public void dispose() {

    }
}
