package org.eu.hanana.cirno.csgo.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import org.eu.hanana.cirno.csgo.Gobox;
import org.eu.hanana.cirno.csgo.Props;
import org.eu.hanana.cirno.csgo.api.ButtonBase;
import org.eu.hanana.cirno.csgo.api.GuiBase;
import org.eu.hanana.cirno.csgo.plugins.Ploader;
import org.eu.hanana.cirno.csgo.res.I18n;
import org.eu.hanana.cirno.csgo.screen.Sutil;

public class ConnectionScreen extends GuiBase {
    @Override
    public void init() {
        super.init();
        buttonList.add(new ButtonBase(buttonList.size(),x+Props.getXSize(0.3f),y+Props.getYSize(0.3f),Props.getXSize(0.4f),Props.getXSize(0.4f), I18n.get("menu.conn"),this));
    }

    @Override
    public void size() {
        x=0;
        dy=Props.getYSize(1)-Gobox.getInstance().getScreen("bar").dy;
        y=0;
        dx=Props.getXSize(1);
    }

    @Override
    public void drawBackground(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        ScreenUtils.clear(Color.PURPLE);

    }

    @Override
    public void drawForeground(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        batch.begin();
        Sutil.drawText(batch,"LOADING...\nStage: "+ Ploader.LS,(int)(dx*.1),(int)(dy*.2),1);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
