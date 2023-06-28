package org.eu.hanana.cirno.csgo.res;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TexManager {
    public static final Map<ResLoc,Texture> textureMap = new HashMap<>();
    private final Texture texture;
    public TexManager(ResLoc rl){
        if (textureMap.containsKey(rl)){
            this.texture=textureMap.get(rl);
        }else {
            this.texture = new Texture(rl.domain + "/" + rl.path);
            textureMap.put(rl,this.texture);
        }
    }
    public Texture getTexture(){
        return texture;
    }
    public static void dispose(){
        for (Texture value : textureMap.values()) {
            value.dispose();
        }
    }
}
