package org.eu.hanana.cirno.csgo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import org.eu.hanana.cirno.csgo.api.GuiBase;
import org.eu.hanana.cirno.csgo.enums.KeyAct;
import org.eu.hanana.cirno.csgo.gui.BarScreen;
import org.eu.hanana.cirno.csgo.loader.PluginLoader;
import org.eu.hanana.cirno.csgo.loader.PluginProp;
import org.eu.hanana.cirno.csgo.res.TexManager;
import org.eu.hanana.cirno.csgo.screen.Sutil;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Gdx.gl;

public class Gobox extends ApplicationAdapter {
	public Map<Integer, Boolean> keyMap = new HashMap<>();
	private static Gobox Instance;
	public Gobox(){
		Instance=this;

	}

	public static Gobox getInstance() {
		return Instance;
	}
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	private final Map<String,GuiBase> CurrentScreens=new HashMap<>();
	public void addScreen(String id,GuiBase screen){
		CurrentScreens.put(id,screen);
		screen.init();
	}
	public void rmScreen(String id){
		CurrentScreens.get(id).dispose();
		CurrentScreens.remove(id);
	}
	public GuiBase getScreen(String id){
		return CurrentScreens.get(id);
	}
	@Override
	public void create () {
		shapeRenderer=new ShapeRenderer();
		Gdx.input.setInputProcessor(new CInputProcessor());
		batch=new SpriteBatch();
		new PluginLoader();
	}
	public void ProcessInput(int code, KeyAct act){

	}
	@Override
	public void resize(int width, int height) {
		for (GuiBase s : CurrentScreens.values()) {
			s.size();

		}
	}
	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		for (GuiBase s : CurrentScreens.values()) {
			gl.glEnable (GL20.GL_SCISSOR_TEST);
			gl.glScissor (s.x, s.y, s.dx, s.dy);
			s.drawAll(batch,shapeRenderer);
			gl.glDisable (GL20.GL_SCISSOR_TEST);

		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		TexManager.dispose();
		Sutil.getInstance().dispose();
	}
}
