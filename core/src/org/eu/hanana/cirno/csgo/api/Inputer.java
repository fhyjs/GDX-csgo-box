package org.eu.hanana.cirno.csgo.api;

import com.badlogic.gdx.InputProcessor;
import org.eu.hanana.cirno.csgo.enums.KeyAct;

public interface Inputer extends InputProcessor {
    default void KeyEvent(int code, KeyAct act){

    }
    @Override
    public default boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public default boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public default boolean keyTyped(char character) {
        return false;
    }

    @Override
    public default boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public default boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public default boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public default boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public default boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
