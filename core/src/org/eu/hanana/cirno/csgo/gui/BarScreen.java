package org.eu.hanana.cirno.csgo.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import org.eu.hanana.cirno.csgo.Props;
import org.eu.hanana.cirno.csgo.api.ButtonBase;
import org.eu.hanana.cirno.csgo.api.GuiBase;

import static com.badlogic.gdx.Gdx.gl;

public class BarScreen extends GuiBase {
    @Override
    public void init() {
        super.init();
        buttonList.add(new ButtonBase(0,x+Props.getXSize(0.03f),y+Props.getYSize(0.03f),Props.getXSize(0.1f),Props.getYSize(0.08f),"X"));
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
