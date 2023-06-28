package org.eu.hanana.cirno.csgo;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import org.eu.hanana.cirno.csgo.enums.KeyAct;

public class CInputProcessor implements InputProcessor {
    ApplicationListener applicationListener = Gdx.app.getApplicationListener();
    public boolean keyDown (int keycode) {
        ((Gobox)applicationListener).keyMap.put(keycode,true);
        ((Gobox)applicationListener).ProcessInput(keycode, KeyAct.Down);
        return true;
    }

    public boolean keyUp (int keycode) {
        ((Gobox)applicationListener).keyMap.put(keycode,false);
        ((Gobox)applicationListener).ProcessInput(keycode,KeyAct.UP);
        return true;
    }

    public boolean keyTyped (char character) {
        return true;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return true;
    }

    public boolean scrolled (float amountX, float amountY) {
        return false;
    }
}

