package com.seitzsoftware.game.state;

import android.view.MotionEvent;

import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;
import com.seitzsoftware.framework.util.Painter;

/**
 * Created by Kevin on 4/28/2016.
 */
public abstract class State {
    public void setCurrentState(State newState) {
        GameMainActivity.sGame.setCurrentState(newState);
    }

    public abstract void init();

    public abstract void update(float delta);

    public abstract void render(Painter g);

    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);
}