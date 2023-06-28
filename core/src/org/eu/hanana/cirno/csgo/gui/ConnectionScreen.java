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
        buttonList.add(new ButtonBase(buttonList.size(),x+Props.getXSize(0.45f),y+Props.getYSize(0.45f),Props.getXSize(0.1f),Props.getYSize(0.1f), I18n.get("menu.conn"),this));
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
        super.drawForeground(batch, shapeRenderer);
        batch.begin();
        Sutil.drawText(batch,I18n.get(Props.IsConnect()?"conn.ok":"conn.no"),Props.BaseW*.42f,Props.BaseH*.8f,1,Props.IsConnect()?Color.GREEN: Color.RED);
        batch.setColor(Color.WHITE);
        Sutil.drawText(batch,Ploader.LS,(int)((Props.BaseW*.1)),(int)(Props.BaseH*.2),1,Color.WHITE);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
