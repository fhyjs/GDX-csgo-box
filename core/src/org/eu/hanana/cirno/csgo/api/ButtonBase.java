package org.eu.hanana.cirno.csgo.api;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.eu.hanana.cirno.csgo.CInputProcessor;
import org.eu.hanana.cirno.csgo.Props;
import org.eu.hanana.cirno.csgo.res.ResLoc;
import org.eu.hanana.cirno.csgo.res.TexManager;
import org.eu.hanana.cirno.csgo.screen.Sutil;

import javax.security.auth.callback.Callback;

import static com.badlogic.gdx.Gdx.gl;

public class ButtonBase implements Inputer{
    public final GuiBase callback;
    public ResLoc tex=new ResLoc("cirno:button.png");
    public boolean enable;
    public int id,x,y,height,width;
    public String text;
    public ButtonBase(int id, int x, int y, int width, int height, String text, GuiBase callback){
        this.x=x;
        this.y=y;
        this.height=height;
        this.width=width;
        this.id=id;
        this.text=text;
        this.enable=true;
        this.callback=callback;
        CInputProcessor.inputProcessors.add(this);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (enable) {
            if (Props.getMouseX() > x * Props.scaleW && Props.getMouseX() < x * Props.scaleW + width * Props.scaleW && Props.getMouseY() > y * Props.scaleH && Props.getMouseY() < y * Props.scaleH + height * Props.scaleH)
                callback.action(id);
        }
        return true;
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        batch.begin();
        Sprite sprite = new Sprite(new TexManager(tex).getTexture()); // Creates a sprite from a Texture
        sprite.setScale((float) width /sprite.getTexture().getWidth(), (float) height /sprite.getTexture().getHeight()); // Scale the sprite
        sprite.setPosition(x,y);
        if (enable){
            if (Props.getMouseX()>x*Props.scaleW&&Props.getMouseX()<x*Props.scaleW+width*Props.scaleW&&Props.getMouseY()>y*Props.scaleH&&Props.getMouseY()<y*Props.scaleH+height*Props.scaleH)
                if (Props.MouseDown)
                    sprite.setColor(0.3f,0.3f,1f,1f);
                else
                    sprite.setColor(0.7f,0.7f,1f,1f);
            else
                sprite.setColor(Color.WHITE);
        }
        sprite.draw(batch);
        Sutil.drawText(batch,text,x,y+height/2,1);
        batch.end();
    }
}
