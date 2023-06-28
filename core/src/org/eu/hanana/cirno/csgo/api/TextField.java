package org.eu.hanana.cirno.csgo.api;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.eu.hanana.cirno.csgo.CInputProcessor;
import org.eu.hanana.cirno.csgo.Props;
import org.eu.hanana.cirno.csgo.api.Widght;
import org.eu.hanana.cirno.csgo.res.ResLoc;
import org.eu.hanana.cirno.csgo.res.TexManager;
import org.eu.hanana.cirno.csgo.screen.Sutil;

public class TextField implements Widght,Inputer {
    public ResLoc tex=new ResLoc("cirno:button.png");
    public boolean enable;
    public int id,x,y,height,width;
    public String text;
    public TextField(int id, int x, int y, int width, int height, String text){
        this.x=x;
        this.y=y;
        this.height=height;
        this.width=width;
        this.id=id;
        this.text=text;
        this.enable=true;
        CInputProcessor.inputProcessors.add(this);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (enable) {
            if (Props.getMouseX() > x * Props.scaleW && Props.getMouseX() < x * Props.scaleW + width * Props.scaleW && Props.getMouseY() > y * Props.scaleH && Props.getMouseY() < y * Props.scaleH + height * Props.scaleH)

        }
        return true;
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        batch.begin();
        Sprite sprite = new Sprite(new TexManager(tex).getTexture()); // Creates a sprite from a Texture
        sprite.setScale((float) width /sprite.getTexture().getWidth(), (float) height /sprite.getTexture().getHeight()); // Scale the sprite
        if (enable){
            if (Props.getMouseX()>x&&Props.getMouseX()<x+width&&Props.getMouseY()>y&&Props.getMouseY()<y+height)
                if (Props.MouseDown)
                    batch.setColor(0.3f,0.3f,1f,1f);
                else
                    batch.setColor(0.7f,0.7f,1f,1f);
            else
                batch.setColor(Color.WHITE);
        }
        batch.draw(sprite.getTexture(), x,y,width,height);
        Sutil.drawText((Batch) batch,text, (int) (x+width*0.1f),y+height-10,1,Color.WHITE);
        batch.end();
    }
    @Override
    public void dispose() {
        CInputProcessor.inputProcessors.remove(this);
    }
}
