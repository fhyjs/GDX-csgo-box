package org.eu.hanana.cirno.csgo;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import org.eu.hanana.cirno.csgo.api.Inputer;
import org.eu.hanana.cirno.csgo.enums.KeyAct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CInputProcessor implements InputProcessor {
    public static Map<Integer, Boolean> keyMap = new HashMap<>();
    public static final List<Inputer> inputProcessors = new ArrayList<>();

    public boolean keyDown (int keycode) {
        keyMap.put(keycode,true);
        for (Inputer inputProcessor : inputProcessors) {
            inputProcessor.KeyEvent(keycode,KeyAct.Down);
        }
        return true;
    }

    public boolean keyUp (int keycode) {
        keyMap.put(keycode,false);
        for (Inputer inputProcessor : inputProcessors) {
            inputProcessor.KeyEvent(keycode,KeyAct.UP);
        }
        return true;
    }

    public boolean keyTyped (char character) {
        for (Inputer inputProcessor : inputProcessors) {
            inputProcessor.keyTyped(character);
        }
        return true;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        Props.MouseDown=true;
        for (Inputer inputProcessor : inputProcessors) {
            inputProcessor.touchDown(x, y, pointer, button);
        }
        return true;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        Props.MouseDown=false;
        List<Inputer> Tmp=new ArrayList<>(inputProcessors);
        for (Inputer inputProcessor : Tmp) {
            inputProcessor.touchUp(x, y, pointer, button);
        }
        return true;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        for (Inputer inputProcessor : inputProcessors) {
            inputProcessor.touchDragged(x, y, pointer);
        }
        return true;
    }

    public boolean mouseMoved (int x, int y) {
        Props.MouseX=x;
        Props.MouseY=y;
        for (Inputer inputProcessor : inputProcessors) {
            inputProcessor.mouseMoved(x, y);
        }
        return true;
    }

    public boolean scrolled (float amountX, float amountY) {
        for (Inputer inputProcessor : inputProcessors) {
            inputProcessor.scrolled(amountX, amountY);
        }
        return true;
    }
}

