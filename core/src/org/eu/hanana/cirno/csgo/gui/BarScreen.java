package org.eu.hanana.cirno.csgo.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.eu.hanana.cirno.csgo.Props;
import org.eu.hanana.cirno.csgo.api.GuiBase;

public class BarScreen extends GuiBase {
    @Override
    public void init() {
        super.init();
    }

    @Override
    public void size() {
        x=0;
        dy=Props.getYSize(0.1f);
        y=Props.getYSize(1)-dy;
        dx=Props.getXSize(1);
    }

    @Override
    public void drawBackground(SpriteBatch batch) {
        ScreenUtils.clear(Color.GREEN);
    }

    @Override
    public void drawForeground(SpriteBatch batch) {
        
    }

    @Override
    public void dispose() {

    }
}
