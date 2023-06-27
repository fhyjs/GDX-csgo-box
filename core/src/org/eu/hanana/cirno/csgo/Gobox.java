package org.eu.hanana.cirno.csgo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.eu.hanana.cirno.csgo.api.GuiBase;
import org.eu.hanana.cirno.csgo.gui.BarScreen;
import org.eu.hanana.cirno.csgo.loader.PluginLoader;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Gdx.gl;

public class Gobox extends ApplicationAdapter {
	private static Gobox Instance;
	public Gobox(){
		Instance=this;

	}

	public static Gobox getInstance() {
		return Instance;
	}

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
		batch=new SpriteBatch();
		new PluginLoader();
		addScreen("bar",new BarScreen());
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
			s.drawAll(batch);
			gl.glDisable (GL20.GL_SCISSOR_TEST);

		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
