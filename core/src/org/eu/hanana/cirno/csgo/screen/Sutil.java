package org.eu.hanana.cirno.csgo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.eu.hanana.cirno.csgo.Gobox;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.graphics.GL20.*;

public class Sutil {
    private static Sutil Instance;
    public Sutil(){
        Instance=this;

    }

    public static Sutil getInstance() {
        return Instance;
    }

    private BitmapFont font=null;
    public void drawText0(Batch batch, String str, float x, float y, float size, Color c){
        if (font==null)
            font=new BitmapFont(Gdx.files.internal("cirno/msyh/msyh.fnt"));
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        font.getData().setScale(size,size);
        font.setColor(c);
        font.draw(batch,str,x,y);
    }
    public static void drawText(Batch batch, String str, float x, float y, float size,Color c){
        Sutil.getInstance().drawText0(batch,str,x,y,size,c);
    }
    public void dispose(){
        font.dispose();
    }
}
