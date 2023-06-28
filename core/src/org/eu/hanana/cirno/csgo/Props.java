package org.eu.hanana.cirno.csgo;

import com.badlogic.gdx.Gdx;

public class Props {
    public static int MouseX,MouseY,BaseW,BaseH,CW,CH;
    public static float scaleW,scaleH;
    public static boolean MouseDown;

    public static int getXSize(float h){
        return (int) (Gdx.graphics.getWidth()*h);
    }
    public static int getYSize(float h){
        return (int) (Gdx.graphics.getHeight()*h);
    }
    public static void resize(){
        CW=Gdx.graphics.getWidth();
        CH=Gdx.graphics.getHeight();
        scaleH= (float) CH /BaseH;
        scaleW= (float) CW /BaseW;
    }
    public static int getMouseX(){
        return MouseX;
    }

    public static int getMouseY() {
        return Gdx.graphics.getHeight()-MouseY;
    }
}
