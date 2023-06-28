package org.eu.hanana.cirno.csgo.api;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.eu.hanana.cirno.csgo.res.ResLoc;
import org.eu.hanana.cirno.csgo.res.TexManager;
import org.eu.hanana.cirno.csgo.screen.Sutil;

import static com.badlogic.gdx.Gdx.gl;

public class ButtonBase {
    public ResLoc tex=new ResLoc("cirno:button.png");
    public int id,x,y,height,width;
    public String text;
    public ButtonBase(int id,int x,int y,int width,int height,String text){
        this.x=x;
        this.y=y;
        this.height=height;
        this.width=width;
        this.id=id;
        this.text=text;
    }
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        batch.begin();
        Sprite sprite = new Sprite(new TexManager(tex).getTexture()); // Creates a sprite from a Texture
        sprite.setScale((float) width /sprite.getTexture().getWidth(), (float) height /sprite.getTexture().getHeight()); // Scale the sprite
        sprite.setPosition(x,y);
        sprite.draw(batch);
        Sutil.drawText(batch,text,x,y+height/2,1);
        batch.end();
    }
}
