package org.eu.hanana.cirno.csgo.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import org.eu.hanana.cirno.csgo.Gobox;
import org.eu.hanana.cirno.csgo.Props;
import org.eu.hanana.cirno.csgo.api.GuiBase;

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
        
    }

    @Override
    public void dispose() {

    }
}
