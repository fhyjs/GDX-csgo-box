package org.eu.hanana.cirno.csgo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.eu.hanana.cirno.csgo.Gobox;

public class Sutil {
    private static Sutil Instance;
    public Sutil(){
        Instance=this;

    }

    public static Sutil getInstance() {
        return Instance;
    }

    private BitmapFont font=null;
    public void drawText0(Batch batch,String str,int x,int y,float size){
        if (font==null)
            font=new BitmapFont(Gdx.files.internal("cirno/msyh/msyh.fnt"));
        font.draw(batch,str,x,y);
    }
    public static void drawText(Batch batch, String str, int x, int y, float size){
        Sutil.getInstance().drawText0(batch,str,x,y,size);
    }
    public void dispose(){
        font.dispose();
    }
}
