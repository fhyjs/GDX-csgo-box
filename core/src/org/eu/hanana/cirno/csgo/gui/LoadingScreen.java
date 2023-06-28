package org.eu.hanana.cirno.csgo.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import org.eu.hanana.cirno.csgo.Gobox;
import org.eu.hanana.cirno.csgo.Props;
import org.eu.hanana.cirno.csgo.api.GuiBase;
import org.eu.hanana.cirno.csgo.plugins.Ploader;
import org.eu.hanana.cirno.csgo.screen.Sutil;

public class LoadingScreen extends GuiBase {
    @Override
    public void init() {
        super.init();
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
        Sutil.drawText(batch,"LOADING...\nStage: "+ Ploader.LS,(int)(dx*.1),(int)(dy*.2),1,Color.WHITE);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
