package org.eu.hanana.cirno.csgo;

import com.badlogic.gdx.Gdx;

public class Props {
    public static int getXSize(float h){
        return (int) (Gdx.graphics.getWidth()*h);
    }
    public static int getYSize(float h){
        return (int) (Gdx.graphics.getHeight()*h);
    }
}
